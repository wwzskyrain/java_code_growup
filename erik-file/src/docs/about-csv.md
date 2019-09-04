# csv学习之旅
## 1.学习背景
工作中经常从xql后台以csv文件格式导出表数据，而之前也用过opencsv的jar包工具，还是挺好用的。
也可能是因为csv文件本身就很简单。所以此次学习的重点在于阅读opencsv的源码。学习要点
1.  如何使用注解来传递数据，比如用@CsvBindByName来接收数据绑定的规则
2.  如何实现对自定义Converter的应用
3.  整个opencsv代码运行流程

## 2.best practice
### 1.解析csv到bean
由于csv本身就是一个文本文件，所以利用opencsv对csv文件进行一行一行的读出就显得没有意义了。
所以这里直接讲scv到bean。具体步骤如下【省略了】，不过可以参见这篇文章，很详细讲的
1.  [Read / Write CSV files in Java using OpenCSV](https://www.callicoder.com/java-read-write-csv-file-opencsv/)
2.  [Read write CSV file in Java – OpenCSV tutorial](https://howtodoinjava.com/library/parse-read-write-csv-opencsv/#bind)
### 2.自定义Converter实现对json值的解析
1.  自定义JsonToMapConverter类，并应用到@CsvCustomBindByName注解的属性上去
2.  

## 3.小结：
1.  log配置
2.  通过设置quoteChar来避免opencsv把字段中的逗号当做分隔符。
3.  自定义Converter并使用它