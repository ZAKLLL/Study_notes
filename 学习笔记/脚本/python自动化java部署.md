```python
import os
import re
import shutil

# 自定义带层级的 文件遍历


def lwalk(top, topdown=True, followlinks=False, max_level=None):
    if max_level is None:
        new_max_level = None
    else:
        if max_level == 0:
            return
        else:
            new_max_level = max_level-1
    top = os.fspath(top)
    dirs, nondirs, walk_dirs = [], [], []
    with os.scandir(top) as it:
        for entry in it:
            if entry.is_dir():
                dirs.append(entry.name)
            else:
                nondirs.append(entry.name)
            if not topdown and entry.is_dir():
                if followlinks or not entry.is_symlink():
                    walk_dirs.append(entry.path)
        if topdown:
            yield top, dirs, nondirs
            for dirname in dirs:
                new_path = os.path.join(top, dirname)
                if followlinks or not os.path.islink(new_path):
                    yield from lwalk(new_path, topdown, followlinks, new_max_level)
        else:
            for new_path in walk_dirs:
                yield from lwalk(new_path, topdown, followlinks, new_max_level)
            yield top, dirs, nondirs


jarLocationMap = {}
jarPidInfoMap = {}


# 获取每个jar所在的地址

javaAppLocation = 'D:\Projects\SPMS\packageWeb'

print('默认java 后台应用文件夹地址为-->'+javaAppLocation)
print('如果需要更改文件夹地址,请输入新的地址,否则请执行空格回车(使用默认地址)')
customJavaAppLocation = input().strip()
if customJavaAppLocation:
    print('后台应用目录地址变更为--->'+customJavaAppLocation)
    javaAppLocation = customJavaAppLocation


for root, dirs, _ in lwalk(javaAppLocation, topdown=True, followlinks=False, max_level=1):
    for sDir in dirs:
        targetJarDirPath = os.path.join(root, sDir)
        for targetJarRoot, _, files in lwalk(targetJarDirPath, topdown=True, followlinks=False, max_level=1):
            f = 0
            for fileName in files:
                if fileName.endswith('.jar'):
                    jarLocationMap[fileName] = os.path.join(
                        targetJarRoot, fileName)
                    jarLocationMap[fileName+'_dir'] = targetJarRoot
                    f += 1
                elif fileName.endswith('.bat') or fileName.endswith('.cmd'):
                    jarLocationMap[fileName +
                                   '_shell'] = os.path.join(targetJarRoot, fileName)
                    f += 1
                if f == 2:
                    break


# 获取运行的jar应用信息
def genJarInfos():
    jarinfos = os.popen('jps')
    for i in jarinfos:
        jarInfo = i.split(' ')
        pid = jarInfo[0]
        jpsJarName = jarInfo[1][0:-1]
        if not jpsJarName or jpsJarName.lower() != 'jps':
            jarPidInfoMap[pid] = {'pid': pid, 'jpsJarName': jpsJarName}

            processInfos = os.popen("netstat -ano|findstr " + pid)
            # status= processInfos.pop()
            # if(status!='0'):
            #     print('\033[7;31m 执行 netstat -ano|findstr ' + pid +' 异常，请开发人员排查 \033[1;31;40m')
            index = 0
            for processInfo in processInfos:
                pInfo = re.split(r" +", processInfo)
                if pInfo[3].lower() == 'listening' and pInfo[1].lower()[0] != '[':
                    jarPidInfoMap[pid]['port:'+index] = pInfo[1].split(':')[1]
            wmicCommand = 'wmic process where processid={0} get commandline'.format(
                pid)
            print(wmicCommand)
            wmicCommandRet = os.popen(wmicCommand)
            realJarName = ''
            for i in wmicCommandRet:
                if i and i.startswith('java'):
                    realJarName = re.split(r" +", i)[-2]
                    break
            if not realJarName.endswith('.jar'):
                continue
            jarPidInfoMap[pid]['realJarName'] = realJarName
            jarPidInfoMap[pid]['jarPath'] = jarLocationMap[realJarName]

    print("本服务器正在运行的jar服务")
    for i in jarPidInfoMap:
        print(jarPidInfoMap[i])


genJarInfos()


killPid = ''

# 查杀对应的java应用


def killJarApp():
    print('--------------------------------------------------------------------')
    print("请输入对应的jar服务pid,(空格回车退出)")
    killPid = input().strip()
    if not killPid:
        exit(0)

    terminateProcessCmd = 'wmic process where processid={0} call terminate'.format(
        killPid)
    deleteProcessCmd = 'wmic process where name={0} delete'.format(killPid)

    print('开始关闭jar 应用')
    print(jarPidInfoMap[killPid])
    ret = os.system(terminateProcessCmd)
    os.system(deleteProcessCmd)
    if ret == 0:
        print("成功关闭jar应用")
    else:
        print(
            '\033[7;31m 执行 terminateProcessCmd 异常，请开发人员排查   \033[1;31;40m' + terminateProcessCmd)
        exit(1)


killJarApp()
print("jar包关闭完毕 ：）")


realJarName = ''
jarDirpath = ''
# 备份jar包


def backUpJar():

    realJarName = jarPidInfoMap[killPid]['realJarName']
    jarDirpath = jarLocationMap[realJarName+'_dir']

    for root, _, files in lwalk(jarDirpath, topdown=True, followlinks=False, max_level=1):
        flag = False
        for fileName in files:
            print('-------------'+os.path.join(root, fileName))

            if fileName.endswith('.jar'):
                jarFileName = fileName
                absoluteJarPath = os.path.join(root, fileName)
                absolutebackupDirPath = os.path.join(root, 'backup')
                print('absolutebackupDirPath------->'+absolutebackupDirPath)
                if not os.path.exists(absolutebackupDirPath):
                    os.mkdir(absolutebackupDirPath)
                    print('不存在back up 文件夹,自动创建....')

                for backUpRoot, _, backupFiles in lwalk(absolutebackupDirPath, topdown=True, followlinks=False, max_level=1):
                    for backFileName in backupFiles:
                        if backFileName == jarFileName:
                            absolutebackupjarPath = os.path.join(
                                backUpRoot, backFileName)
                            print('删除现有备份文件-->'+absolutebackupjarPath)
                            os.remove(absolutebackupjarPath)
                            break
                    break
                # 执行备份操作
                shutil.move(absoluteJarPath, absolutebackupDirPath)
                print('备份成功')
                os.system('start '+root)
                flag = True
                break
        if flag:
            break


backUpJar()
print("jar包备份完毕 ：）\n 是否需要进行自动更新重启(Y/N)")
updateFlag = input().strip() == 'Y'
if (not updateFlag):
    exit(0)


# 重新启动指定文件夹下的java应用
def restartNewJarApp():
    # 在当前文件目录下寻找一个叫updataJars的文件夹
    relativeUpdateJarsPath = os.path.join(".", 'updataJars')
    if not os.path.exists(relativeUpdateJarsPath):
        os.mkdir(relativeUpdateJarsPath)
        print('不存在updataJars 文件夹,自动创建....')
        exit(0)

    existTargetJar = False
    # 复制转移文件remo
    for root, _, files in lwalk(relativeUpdateJarsPath, topdown=True, followlinks=False, max_level=1):
        for newJarFileName in files:
            if newJarFileName == realJarName:
                absluteNewJarFilePath = os.path.join(root, newJarFileName)
                shutil.move(absluteNewJarFilePath, jarDirpath)
                existTargetJar = True
                break

    if not existTargetJar:
        exit(1)

    # 执行重启行为
    targetJarPath = jarLocationMap[realJarName]
    shellPath = jarLocationMap[realJarName+'_shell']

    shellContent = ''

    try:
        fh = open(shellPath, "r")
        shellContent = fh.read()
    except IOError:
        exit(0)
    else:
        fh.close()

    if shellContent.find('./'+realJarName) != -1:
        shellContent = shellContent.replace('./'+realJarName, targetJarPath, 1)
    elif shellContent.find(realJarName) != -1:
        shellContent = shellContent.replace(realJarName, targetJarPath, 1)
    else:
        print('\033[7;31m '+targetJarPath + '不存在   \033[1;31;40m')

    os.popen(shellContent)


restartNewJarApp()

input()

```

