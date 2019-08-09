# Django

+ 在models.py中定义数据库信息:

  + ```python
    from django.db import models
    from django.utils import timezone
    
    import datetime
    
    
    # Create your models here.
    # 每个模型都是models.Model的子类
    class Question(models.Model):
        question_text = models.CharField(max_length=200)
        pub_date = models.DateTimeField('data published')
    
        def __str__(self):
            return self.question_text
    
        def was_published_recently(self):
            return self.pub_date >= timezone.now() - datetime.timedelta(days=1)  # 判断添加时间是否为一天内的
    
    
    class Choice(models.Model):
        question = models.ForeignKey(Question, on_delete=models.CASCADE)  # 设置外键，并且绑定级联删除
        choice_text = models.CharField(max_length=200)  # 选项描述
        votes = models.IntegerField(default=0)  # 选项得票数
    
        def __str__(self):
            return self.choice_text
    ```

  + 在admin.py后台管理中注册models:

    + ```python
      from django.contrib import admin
      from .models import Question
      from .models import Choice
      
      # Register your models here.
      admin.site.register(Question)
      admin.site.register(Choice)
      ```

+ 数据库模型操作:
  + 编辑 `models.py` 文件，改变模型。
  + 运行 [`python manage.py makemigrations`](https://docs.djangoproject.com/zh-hans/2.1/ref/django-admin/#django-admin-makemigrations) 为模型的改变生成迁移文件。
  + 运行 [`python manage.py migrate`](https://docs.djangoproject.com/zh-hans/2.1/ref/django-admin/#django-admin-migrate) 来应用数据库迁移。
  
+ 定义Url路径：

  + ```python
    from django.urls import path
    from . import views
    app_name = 'polls'  # 为Url名称添加命名空间
    urlpatterns = [
        path('', views.index, name='index'),
        path('<int:question_id>/', views.detail, name='detail'),
        path('<int:question_id>/result/', views.results, name='results'),
        path('<int:question_id>/vote/', views.vote, name='vote')
    ]
    ```

+ path()的四个参数：

  + ###  route:

    route` 是一个匹配 URL 的准则（类似正则表达式）。当 Django 响应一个请求时，它会从 `urlpatterns` 的第一项开始，按顺序依次匹配列表中的项，直到找到匹配的项。

    这些准则不会匹配 GET 和 POST 参数或域名。例如，URLconf 在处理请求 `https://www.example.com/myapp/`时，它会尝试匹配 `myapp/` 。处理请求 `https://www.example.com/myapp/?page=3` 时，也只会尝试匹配 `myapp/`。

  + ###  `view`

    当 Django 找到了一个匹配的准则，就会调用这个特定的视图函数，并传入一个 [`HttpRequest`](https://docs.djangoproject.com/zh-hans/2.1/ref/request-response/#django.http.HttpRequest) 对象作为第一个参数，被“捕获”的参数以关键字参数的形式传入。稍后，我们会给出一个例子

  + ###  `kwargs`

    任意个关键字参数可以作为一个字典传递给目标视图函数。本教程中不会使用这一特性。

  + ###  `name`

    为你的 URL 取名能使你在 Django 的任意地方唯一地引用它，尤其是在模板中。这个有用的特性允许你只改一个文件就能全局地修改某个 URL 模式。

+ django模板：

  + 在templates文件夹中新建文件名polls:

    + ![1560861270715](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1560861270715.png)

  + ```python
    from django.http import HttpResponse
    from django.template import loader 
    from .models import Question
    def index(request):
        latest_question_list = Question.objects.order_by('-pub_date')[:5] 
        template = loader.get_template('polls/index.html')  # 载入polls/index.html模板文件
        context = {
            'latest_question_list': latest_question_list, 
        }												 
        return HttpResponse(template.render(context, request))  # 向模板文件传递context上下文对象
    			#载入模板，填充上下文，再返回由它生成的 HttpResponse 对象
    ```

    ```html
    {# <li><a href="/polls/{{ question.id }}/">{{ question.question_text }}</a></li>#}
       <li><a href="{% url 'polls:detail' question.id %}">{{ question.question_text }}</a></li>
     {# 使用urls注册时声明的name替换url硬编码,降低耦合,polls是命名空间，防止命名冲突 #}
    
    ```

  + 使用快捷函数render(无需导入loader包):

    ```python
    def index(request):
        latest_question_list = Question.objects.order_by('-pub_date')[0:5]
        context = {
            'latest_question_list': latest_question_list
        }
        return render(request, 'polls/index.html', context)
    ```

  + Http404界面：

    + ```python
      from django.http import Http404
      def detail(request, question_id):
          try:
              question = Question.objects.get(pk=question_id)
          except Question.DoesNotExist:
              raise Http404("Question does not exist") #抛出一个http404异常 相当于java中的throw
          return render(request, 'polls/detail.html', {'question': question})
      ```

    + 使用get_object_or_404()函数

      ```python
      from django.shortcuts import render, get_object_or_404
      def detail(request, question_id):
          question = get_object_or_404(Question, pk=question_id)
          return render(request, 'polls/detail.html', {'question': question})
      ```

      

+ 使用通用模板listview和Detailview:

  + ```python
    class IndexView(generic.ListView):
        template_name = 'polls/index.html'
        context_object_name = 'latest_question_list'
        def get_queryset(self):
            return Question.objects.order_by('-pub_date')[:5]
        
    class DetailView(generic.DetailView):
        model = Question
        template_name = 'polls/detail.html'
        
    class ResultsView(generic.DetailView):
        model = Question
        template_name = 'polls/results.html'
        
    def vote(request, question_id):
        question = get_object_or_404(Question, pk=question_id)
        try:
            selected_choice = question.choice_set.get(
                pk=request.POST['choice'])  # request.POST['choice'] 以字符串形式返回选择的 Choice 的 ID。 request.POST 的值永远是字符串。
        except (KeyError, Choice.DoesNotExist):  # 当用户没有选择就进行提交时
            # Redisplay the question voting form.
            return render(request, 'polls/detail.html', {
                'question': question,
                'error_message': "You didn't select a choice.",
            })
        else:
            selected_choice.votes += 1
            selected_choice.save()
            # Always return an HttpResponseRedirect after successfully dealing
            # with POST data. This prevents data from being posted twice if a
            # user hits the Back button.
            return HttpResponseRedirect(reverse('polls:results', args=(question.id,)))  # reverse() 加载url的name属性
    ```

  + 