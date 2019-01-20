1.  背景:运营总是拿一个excel文件来让我帮忙生成sql语句。之前草草的写过，今天把它规范化一下。
2.  稍稍值得笔记的几点：
    1.  整个程序用到了技术，并对部分细节做说明
        1.  excel2007的读写
        2.  java的文件io
        3.  poi读取各种类型的数据：numerical、string、date
        4.  自定义字符串格式化工具：StringFormat
    2.  poi对整形数据的读写很差劲，总是读出来double这种浮点数，
        还好有BigDecima来进行转化并格式话。
    3.  poi对日期类型的cell，其类型也是Numerical的，但是好像就不能细分了，可以直接用
        `Date date = cell.getDateCellValue();`来读取。
    4.  StringFormat是对String.format()的改造，其中pattern中的占位符都是{keyName}
        形式的，在赋值时通过一个Map，将patter中的key都替换成Map中的value；替换按照Map中
        key的名字来进行；所以，如果pattern中的某些key，在Map中没有对应，那么就原样保留。