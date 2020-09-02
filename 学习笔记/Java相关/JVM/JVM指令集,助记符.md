 ##JVM指令集（指令码、助记符、功能描述）

| **指令码** | **助记符**          | **功能描述**                                                 |
| ---------- | ------------------- | ------------------------------------------------------------ |
| 0x00       | **nop**             | 无操作                                                       |
|            |                     |                                                              |
| 0x01       | **aconst_null**     | 指令格式： aconst_null 功能描述： null进栈。 指令执行前指令执行后栈底...... null栈顶 注意：JVM并没有为null指派一个具体的值。 |
|            |                     |                                                              |
| 0x02       | **iconst_m1**       | int型常量值-1进栈                                            |
| 0x03       | **iconst_0**        | int型常量值0进栈                                             |
| 0x04       | **iconst_1**        | int型常量值1进栈                                             |
| 0x05       | **iconst_2**        | int型常量值2进栈                                             |
| 0x06       | **iconst_3**        | int型常量值3进栈                                             |
| 0x07       | **iconst_4**        | int型常量值4进栈                                             |
| 0x08       | **iconst_5**        | int型常量值5进栈                                             |
|            |                     |                                                              |
| 0x09       | **lconst_0**        | long型常量值0进栈                                            |
| 0x0A       | **lconst_1**        | long型常量值1进栈                                            |
|            |                     |                                                              |
| 0x0B       | **fconst_0**        | float型常量值0进栈                                           |
| 0x0C       | **fconst_1**        | float型常量值1进栈                                           |
| 0x0D       | **fconst_2**        | float型常量值2进栈                                           |
|            |                     |                                                              |
| 0x0E       | **dconst_0**        | double型常量值0进栈                                          |
| 0x0F       | **dconst_1**        | double型常量值1进栈                                          |
|            |                     |                                                              |
| 0x10       | **bipush**          | 将一个byte型常量值推送至栈顶                                 |
| 0x11       | **sipush**          | 将一个short型常量值推送至栈顶                                |
|            |                     |                                                              |
| 0x12       | **ldc**             | 将int、float或String型常量值从常量池中推送至栈顶             |
| 0x13       | **ldc_w**           | 将int、float或String型常量值从常量池中推送至栈顶（宽索引）   |
| 0x14       | **ldc2_w**          | 将long或double型常量值从常量池中推送至栈顶（宽索引）         |
|            |                     |                                                              |
| 0x15       | **iload**           | 指定的int型局部变量进栈                                      |
| 0x16       | **lload**           | 指定的long型局部变量进栈                                     |
| 0x17       | **fload**           | 指定的float型局部变量进栈                                    |
| 0x18       | **dload**           | 指定的double型局部变量进栈                                   |
| 0x19       | **aload**           | 指令格式： aload index 功能描述： 当前frame的局部变量数组中下标为      index的引用型局部变量进栈 指令执行前指令执行后栈底...... objectref栈顶 index ： 无符号一byte整型。和wide指令联用，      可以使index为两byte。 |
|            |                     |                                                              |
| 0x1A       | **iload_0**         | 第一个int型局部变量进栈                                      |
| 0x1B       | **iload_1**         | 第二个int型局部变量进栈                                      |
| 0x1C       | **iload_2**         | 第三个int型局部变量进栈                                      |
| 0x1D       | **iload_3**         | 第四个int型局部变量进栈                                      |
|            |                     |                                                              |
| 0x1E       | **lload_0**         | 第一个long型局部变量进栈                                     |
| 0x1F       | **lload_1**         | 第二个long型局部变量进栈                                     |
| 0x20       | **lload_2**         | 第三个long型局部变量进栈                                     |
| 0x21       | **lload_3**         | 第四个long型局部变量进栈                                     |
|            |                     |                                                              |
| 0x22       | **fload_0**         | 第一个float型局部变量进栈                                    |
| 0x23       | **fload_1**         | 第二个float型局部变量进栈                                    |
| 0x24       | **fload_2**         | 第三个float型局部变量进栈                                    |
| 0x25       | **fload_3**         | 第四个float型局部变量进栈                                    |
|            |                     |                                                              |
| 0x26       | **dload_0**         | 第一个double型局部变量进栈                                   |
| 0x27       | **dload_1**         | 第二个double型局部变量进栈                                   |
| 0x28       | **dload_2**         | 第三个double型局部变量进栈                                   |
| 0x29       | **dload_3**         | 第四个double型局部变量进栈                                   |
|            |                     |                                                              |
| 0x2A       | **aload_0**         | 指令格式：aload_0 该指令的行为类似于aload指令index为0的情况。 |
| 0x2B       | **aload_1**         | 同上                                                         |
| 0x2C       | **aload_2**         | 同上                                                         |
| 0x2D       | **aload_3**         | 同上                                                         |
|            |                     |                                                              |
| 0x2E       | **iaload**          | 指定的int型数组的指定下标处的值进栈                          |
| 0x2F       | **laload**          | 指定的long型数组的指定下标处的值进栈                         |
| 0x30       | **faload**          | 指定的float型数组的指定下标处的值进栈                        |
| 0x31       | **daload**          | 指定的double型数组的指定下标处的值进栈                       |
| 0x32       | **aaload**          | 指令格式： aaload 功能描述： 栈顶的数组下标（index）、数组引用      （arrayref）出栈，并根据这两个数值      取出对应的数组元素值（value）进栈。 抛出异常： 如果arrayref的值为null，会抛出      NullPointerException。      如果index造成数组越界，会抛出      ArrayIndexOutOfBoundsException。 指令执行前指令执行后栈底......arrayrefvalueindex 栈顶 index   ： int类型arrayref  ： 数组的引用 |
| 0x33       | **baload**          | 指定的boolean或byte型数组的指定下标处的值进栈                |
| 0x34       | **caload**          | 指定的char型数组的指定下标处的值进栈                         |
| 0x35       | **saload**          | 指定的short型数组的指定下标处的值进栈                        |
|            |                     |                                                              |
| 0x36       | **istore**          | 将栈顶int型数值存入指定的局部变量                            |
| 0x37       | **lstore**          | 将栈顶long型数值存入指定的局部变量                           |
| 0x38       | **fstore**          | 将栈顶float型数值存入指定的局部变量                          |
| 0x39       | **dstore**          | 将栈顶double型数值存入指定的局部变量                         |
| 0x3A       | **astore**          | 指令格式： astore index 功能描述： 将栈顶数值（objectref）存入当前      frame的局部变量数组中指定下标      （index）处的变量中，栈顶数值出栈。 指令执行前指令执行后栈底......objectref 栈顶 index ： 无符号一byte整数。该指令和wide联      用，index可以为无符号两byte整数。 |
|            |                     |                                                              |
| 0x3B       | **istore_0**        | 将栈顶int型数值存入第一个局部变量                            |
| 0x3C       | **istore_1**        | 将栈顶int型数值存入第二个局部变量                            |
| 0x3D       | **istore_2**        | 将栈顶int型数值存入第三个局部变量                            |
| 0x3E       | **istore_3**        | 将栈顶int型数值存入第四个局部变量                            |
|            |                     |                                                              |
| 0x3F       | **lstore_0**        | 将栈顶long型数值存入第一个局部变量                           |
| 0x40       | **lstore_1**        | 将栈顶long型数值存入第二个局部变量                           |
| 0x41       | **lstore_2**        | 将栈顶long型数值存入第三个局部变量                           |
| 0x42       | **lstore_3**        | 将栈顶long型数值存入第四个局部变量                           |
|            |                     |                                                              |
| 0x43       | **fstore_0**        | 将栈顶float型数值存入第一个局部变量                          |
| 0x44       | **fstore_1**        | 将栈顶float型数值存入第二个局部变量                          |
| 0x45       | **fstore_2**        | 将栈顶float型数值存入第三个局部变量                          |
| 0x46       | **fstore_3**        | 将栈顶float型数值存入第四个局部变量                          |
|            |                     |                                                              |
| 0x47       | **dstore_0**        | 将栈顶double型数值存入第一个局部变量                         |
| 0x48       | **dstore_1**        | 将栈顶double型数值存入第二个局部变量                         |
| 0x49       | **dstore_2**        | 将栈顶double型数值存入第三个局部变量                         |
| 0x4A       | **dstore_3**        | 将栈顶double型数值存入第四个局部变量                         |
|            |                     |                                                              |
| 0x4B       | **astore_0**        | 指令格式： astore_0 功能描述： 该指令的行为类似于astore指令index      为0的情况。 |
| 0x4C       | **astore_1**        | 同上                                                         |
| 0x4D       | **astore_2**        | 同上                                                         |
| 0x4E       | **astore_3**        | 同上                                                         |
|            |                     |                                                              |
| 0x4F       | **iastore**         | 将栈顶int型数值存入指定数组的指定下标处                      |
| 0x50       | **lastore**         | 将栈顶long型数值存入指定数组的指定下标处                     |
| 0x51       | **fastore**         | 将栈顶float型数值存入指定数组的指定下标处                    |
| 0x52       | **dastore**         | 将栈顶double型数值存入指定数组的指定下标处                   |
| 0x53       | **aastore**         | 指令格式： aastore 功能描述： 根据栈顶的引用型数值（value）、数组下      标（index）、数组引用（arrayref）出      栈，将数值存入对应的数组元素中。 抛出异常： 如果value的类型和arrayref所引用      的数组的元素类型不兼容，会抛出抛出      ArrayStoreException。      如果index造成数组越界，会抛出      ArrayIndexOutOfBoundsException。      如果arrayref值为null，会抛出      NullPointerException。 指令执行前指令执行后栈底......arrayref index value 栈顶 arrayref  ： 必须是对数组的引用index   ： int类型value   ： 引用类型 |
| 0x54       | **bastore**         | 将栈顶boolean或byte型数值存入指定数组的指定下标处            |
| 0x55       | **castore**         | 将栈顶char型数值存入指定数组的指定下标处                     |
| 0x56       | **sastore**         | 将栈顶short型数值存入指定数组的指定下标处                    |
|            |                     |                                                              |
| 0x57       | **pop**             | 栈顶数值出栈 (该栈顶数值不能是long或double型)                |
| 0x58       | **pop2**            | 栈顶的一个（如果是long、double型的)或两个（其它类型的）数值出栈 |
|            |                     |                                                              |
| 0x59       | **dup**             | 复制栈顶数值，并且复制值进栈                                 |
| 0x5A       | **dup_x1**          | 复制栈顶数值，并且复制值进栈2次                              |
| 0x5B       | **dup_x2**          | 复制栈顶数值，并且复制值进栈2次或3次                         |
| 0x5C       | **dup2**            | 复制栈顶一个（long、double型的)或两个（其它类型的）数值，并且复制值进栈 |
| 0x5D       | **dup2_x1**         |                                                              |
| 0x5E       | **dup2_x2**         |                                                              |
|            |                     |                                                              |
| 0x5F       | **swap**            | 栈顶的两个数值互换(要求栈顶的两个数值不能是long或double型的) |
|            |                     |                                                              |
| 0x60       | **iadd**            | 栈顶两int型数值相加，并且结果进栈                            |
| 0x61       | **ladd**            | 栈顶两long型数值相加，并且结果进栈                           |
| 0x62       | **fadd**            | 栈顶两float型数值相加，并且结果进栈                          |
| 0x63       | **dadd**            | 栈顶两double型数值相加，并且结果进栈                         |
|            |                     |                                                              |
| 0x64       | **isub**            | 栈顶两int型数值相减，并且结果进栈                            |
| 0x65       | **lsub**            | 栈顶两long型数值相减，并且结果进栈                           |
| 0x66       | **fsub**            | 栈顶两float型数值相减，并且结果进栈                          |
| 0x67       | **dsub**            | 栈顶两double型数值相减，并且结果进栈                         |
|            |                     |                                                              |
| 0x68       | **imul**            | 栈顶两int型数值相乘，并且结果进栈                            |
| 0x69       | **lmul**            | 栈顶两long型数值相乘，并且结果进栈                           |
| 0x6A       | **fmul**            | 栈顶两float型数值相乘，并且结果进栈                          |
| 0x6B       | **dmul**            | 栈顶两double型数值相乘，并且结果进栈                         |
|            |                     |                                                              |
| 0x6C       | **idiv**            | 栈顶两int型数值相除，并且结果进栈                            |
| 0x6D       | **ldiv**            | 栈顶两long型数值相除，并且结果进栈                           |
| 0x6E       | **fdiv**            | 栈顶两float型数值相除，并且结果进栈                          |
| 0x6F       | **ddiv**            | 栈顶两double型数值相除，并且结果进栈                         |
|            |                     |                                                              |
| 0x70       | **irem**            | 栈顶两int型数值作取模运算，并且结果进栈                      |
| 0x71       | **lrem**            | 栈顶两long型数值作取模运算，并且结果进栈                     |
| 0x72       | **frem**            | 栈顶两float型数值作取模运算，并且结果进栈                    |
| 0x73       | **drem**            | 栈顶两double型数值作取模运算，并且结果进栈                   |
|            |                     |                                                              |
| 0x74       | **ineg**            | 栈顶int型数值取负，并且结果进栈                              |
| 0x75       | **lneg**            | 栈顶long型数值取负，并且结果进栈                             |
| 0x76       | **fneg**            | 栈顶float型数值取负，并且结果进栈                            |
| 0x77       | **dneg**            | 栈顶double型数值取负，并且结果进栈                           |
|            |                     |                                                              |
| 0x78       | **ishl**            | int型数值左移指定位数，并且结果进栈                          |
| 0x79       | **lshl**            | long型数值左移指定位数，并且结果进栈                         |
|            |                     |                                                              |
| 0x7A       | **ishr**            | int型数值带符号右移指定位数，并且结果进栈                    |
| 0x7B       | **lshr**            | long型数值带符号右移指定位数，并且结果进栈                   |
| 0x7C       | **iushr**           | int型数值无符号右移指定位数，并且结果进栈                    |
| 0x7D       | **lushr**           | long型数值无符号右移指定位数，并且结果进栈                   |
|            |                     |                                                              |
| 0x7E       | **iand**            | 栈顶两int型数值按位与，并且结果进栈                          |
| 0x7F       | **land**            | 栈顶两long型数值按位与，并且结果进栈                         |
|            |                     |                                                              |
| 0x80       | **ior**             | 栈顶两int型数值按位或，并且结果进栈                          |
| 0x81       | **lor**             | 栈顶两long型数值按位或，并且结果进栈                         |
|            |                     |                                                              |
| 0x82       | **ixor**            | 栈顶两int型数值按位异或，并且结果进栈                        |
| 0x83       | **lxor**            | 栈顶两long型数值按位异或，并且结果进栈                       |
|            |                     |                                                              |
| 0x84       | **iinc**            | 指定int型变量增加指定值                                      |
|            |                     |                                                              |
| 0x85       | **i2l**             | 栈顶int值强转long值，并且结果进栈                            |
| 0x86       | **i2f**             | 栈顶int值强转float值，并且结果进栈                           |
| 0x87       | **i2d**             | 栈顶int值强转double值，并且结果进栈                          |
| 0x88       | **l2i**             | 栈顶long值强转int值，并且结果进栈                            |
| 0x89       | **l2f**             | 栈顶long值强转float值，并且结果进栈                          |
| 0x8A       | **l2d**             | 栈顶long值强转double值，并且结果进栈                         |
| 0x8B       | **f2i**             | 栈顶float值强转int值，并且结果进栈                           |
| 0x8C       | **f2l**             | 栈顶float值强转long值，并且结果进栈                          |
| 0x8D       | **f2d**             | 栈顶float值强转double值，并且结果进栈                        |
| 0x8E       | **d2i**             | 栈顶double值强转int值，并且结果进栈                          |
| 0x8F       | **d2l**             | 栈顶double值强转long值，并且结果进栈                         |
| 0x90       | **d2f**             | 栈顶double值强转float值，并且结果进栈                        |
| 0x91       | **i2b**             | 栈顶int值强转byte值，并且结果进栈                            |
| 0x92       | **i2c**             | 栈顶int值强转char值，并且结果进栈                            |
| 0x93       | **i2s**             | 栈顶int值强转short值，并且结果进栈                           |
|            |                     |                                                              |
| 0x94       | **lcmp**            | 比较栈顶两long型数值大小，并且结果（1，0，-1）进栈           |
| 0x95       | **fcmpl**           | 比较栈顶两float型数值大小，并且结果（1，0，-1）进栈；当其中一个数值为NaN时， -1进栈 |
| 0x96       | **fcmpg**           | 比较栈顶两float型数值大小，并且结果（1，0，-1）进栈；当其中一个数值为NaN时，1进栈 |
| 0x97       | **dcmpl**           | 比较栈顶两double型数值大小，并且结果（1，0，-1）进栈；当其中一个数值为NaN时，-1进栈 |
| 0x98       | **dcmpg**           | 比较栈顶两double型数值大小，并且结果（1，0，-1）进栈；当其中一个数值为NaN时，1进栈 |
|            |                     |                                                              |
| 0x99       | **ifeq**            | 当栈顶int型数值等于0时跳转                                   |
| 0x9A       | **ifne**            | 当栈顶int型数值不等于0时跳转                                 |
| 0x9B       | **iflt**            | 当栈顶int型数值小于0时跳转                                   |
| 0x9C       | **ifge**            | 当栈顶int型数值大于等于0时跳转                               |
| 0x9D       | **ifgt**            | 当栈顶int型数值大于0时跳转                                   |
| 0x9E       | **ifle**            | 当栈顶int型数值小于等于0时跳转                               |
| 0x9F       | **if_icmpeq**       | 比较栈顶两int型数值大小，当结果等于0时跳转                   |
| 0xA0       | **if_icmpne**       | 比较栈顶两int型数值大小，当结果不等于0时跳转                 |
| 0xA1       | **if_icmplt**       | 比较栈顶两int型数值大小，当结果小于0时跳转                   |
| 0xA2       | **if_icmpge**       | 比较栈顶两int型数值大小，当结果大于等于0时跳转               |
| 0xA3       | **if_icmpgt**       | 比较栈顶两int型数值大小，当结果大于0时跳转                   |
| 0xA4       | **if_icmple**       | 比较栈顶两int型数值大小，当结果小于等于0时跳转               |
| 0xA5       | **if_acmpeq**       | 比较栈顶两引用型数值，当结果相等时跳转                       |
| 0xA6       | **if_acmpne**       | 比较栈顶两引用型数值，当结果不相等时跳转                     |
|            |                     |                                                              |
| 0xA7       | **goto**            | 无条件跳转                                                   |
|            |                     |                                                              |
| 0xA8       | **jsr**             | 跳转至指定16位offset位置，并将jsr下一条指令地址压入栈顶      |
| 0xA9       | **ret**             | 返回至局部变量指定的index的指令位置（通常与jsr、jsr_w联合使用） |
| 0xAA       | **tableswitch**     | 用于switch条件跳转，case值连续（可变长度指令）               |
| 0xAB       | **lookupswitch**    | 用于switch条件跳转，case值不连续（可变长度指令）             |
|            |                     |                                                              |
| 0xAC       | **ireturn**         | 当前方法返回int                                              |
| 0xAD       | **lreturn**         | 当前方法返回long                                             |
| 0xAE       | **freturn**         | 当前方法返回float                                            |
| 0xAF       | **dreturn**         | 当前方法返回double                                           |
| 0xB0       | **areturn**         | 指令格式： areturn 功能描述： 从方法中返回一个对象的引用。 抛出异常： 如果当前方法是`synchronized`方法，      并且当前线程不是改方法的锁的拥有者，      会抛出      IllegalMonitorStateException。     指令执行前指令执行后栈底... objectref 栈顶 objectref ： 被返回的对象引用。 |
| 0xB1       | **return**          | 当前方法返回void                                             |
|            |                     |                                                              |
| 0xB2       | **getstatic**       | 获取指定类的静态域，并将其值压入栈顶                         |
| 0xB3       | **putstatic**       | 为指定的类的静态域赋值                                       |
| 0xB4       | **getfield**        | 获取指定类的实例域，并将其值压入栈顶                         |
| 0xB5       | **putfield**        | 为指定的类的实例域赋值                                       |
|            |                     |                                                              |
| 0xB6       | **invokevirtual**   | 调用实例方法                                                 |
| 0xB7       | **invokespecial**   | 调用超类构造方法、实例初始化方法、私有方法                   |
| 0xB8       | **invokestatic**    | 调用静态方法                                                 |
| 0xb9       | **invokeinterface** | 调用接口方法                                                 |
|            |                     |                                                              |
| 0xBA       | ---                 | 因为历史原因，该码点为未使用的保留码点                       |
|            |                     |                                                              |
| 0xBB       | **new**             | 创建一个对象，并且其引用进栈                                 |
| 0xBC       | **newarray**        | 创建一个基本类型数组，并且其引用进栈                         |
| 0xBD       | **anewarray**       | 指令格式： anewarray index1 index2 功能描述： 栈顶数值（count）作为数组长度，创建      一个引用 型数组。栈顶数值出栈，数组引      用进栈。 抛出异常： 如果count小于0，会抛出      NegativeArraySizeException 指令执行前指令执行后栈底......countarrayref栈顶 count   ： int类型。arrayref  ： 对所创建的数组的引用。 |
| 0xBE       | **arraylength**     | 指令格式： arraylength 功能描述： 栈顶的数组引用（arrayref）出栈，该      数组的长度进栈。 抛出异常： 如果arrayref的值为null，会抛出      NullPointerException。 指令执行前指令执行后栈底......arrayreflength栈顶 arrayref  ： 数组引用length   ： 数组长度 |
|            |                     |                                                              |
| 0xBF       | **athrow**          | 指令格式： athrow 功能描述： 将栈顶的数值作为异常或错误抛出 抛出异常： 如果栈顶数值为null，则使用      NullPointerException代替栈顶数      值抛出。      如果方法是的，则有可能抛出      IllegalMonitorStateException。 指令执行前指令执行后栈底...objectrefobjectref 栈顶 objectref ： Throwable或其子类的实例的引用。 |
| 0xC0       | **checkcast**       | 类型转换检查，如果该检查未通过将会抛出ClassCastException异常 |
| 0xc1       | **instanceof**      | 检查对象是否是指定的类的实例。如果是，1进栈；否则，0进栈     |
|            |                     |                                                              |
| 0xC2       | **monitorenter**    | 获得对象锁                                                   |
| 0xC3       | **monitorexit**     | 释放对象锁                                                   |
|            |                     |                                                              |
| 0xC4       | **wide**            | 用于修改其他指令的行为                                       |
|            |                     |                                                              |
| 0xC5       | **multianewarray**  | 创建指定类型和维度的多维数组（执行该指令时，栈中必须包含各维度的长度值），并且其引用值进栈 |
|            |                     |                                                              |
| 0xC6       | **ifnull**          | 为null时跳转                                                 |
| 0xC7       | **ifnonnull**       | 不为null时跳转                                               |
| 0xC8       | **goto_w**          | 无条件跳转（宽索引）                                         |
| 0xC9       | **jsr_w**           | 跳转至指定32位offset位置，并且jsr_w下一条指令地址进栈        |
|            |                     |                                                              |
| 0xCA       | **breakpoint**      |                                                              |
|            |                     |                                                              |
| 0xFE       | **impdep1**         |                                                              |
| 0xFF       | **impdep2**         |                                                              |