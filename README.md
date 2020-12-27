# QFFThird
实现的功能:
1) 
 云盘的注册，该页面输入的数据在register类（User类中提供register中使用的一些方法）内进行处理，实现的功能有，判断用户名、email、电话号码是否存在；验证码是否正确，若不正确，则在该页面显示相应的错误内容；若以上都正确，则插入数据库中的YunPanUser表内<br>
 ![image](https://github.com/yanyun6/QFFThird/blob/master/%E6%B3%A8%E5%86%8C.png)<br>
![image](https://github.com/yanyun6/QFFThird/blob/master/%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF%E6%95%B0%E6%8D%AE%E8%A1%A8.png)<br>
2）云盘登录：该页面首先判断验证码是否错误，接着判断用户名和密码是否存在，
还可以判断该用户是否已经登录，若已经登录，则无法再次登录（没法进行页面跳转），输入的数据是在login类（user类提供一些方法）中进行处理<br>
 ![image](https://github.com/yanyun6/QFFThird/blob/master/%E7%99%BB%E5%BD%95.png)<br>
3）文件上传/分用户存放/文件分类：
文件上传页面数据是由uploadservlet包（主要负责上传和文件分类）及upload类（主要负责数据库存储）进行，该类实现将文件上传到我的u盘中，并且在u盘中是先分上传者建立文件夹，再分文件类型文件夹，并且进行了文件夹是否存在的逻辑判断，若没有，则创建，有，则直接传到对应的文件夹，并且将上传者，上传文件类型，以及时间存储到数据库中<br>
![image](https://github.com/yanyun6/QFFThird/blob/master/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0.png)<br>
 ![image](https://github.com/yanyun6/QFFThird/blob/master/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E6%88%96%E4%B8%8B%E8%BD%BD.png）<br>
![image](https://github.com/yanyun6/QFFThird/blob/master/%E6%96%87%E4%BB%B6%E4%B8%8A%E4%BC%A0%E4%BF%A1%E6%81%AF%E6%95%B0%E6%8D%AE%E8%A1%A8.png)<br>
4）下载：
该功能就是由DownLoadServlet类实现，只能唯一确定下载某个文件，如果需要下载别的文件，需要在该类中文件路径进行修改，不具有普适性<br>
作业总结/不足：<br>
1）	刚看到作业时，是真的什么也不会，然后就去网上搜视频来看，所有这段时间的学习，个人认为目的性太强了，导致一些内容知识点，直接跳过，没有系统学习或实操太少，如js的相关内容，ajax，json（这两个网课上讲的内容很少，然后和js有关系，看的时候也不是能看的很懂）<br>
2）	前端页面html、css其实加起来一共也就学了三四天，做的注册页面，还是跟着网课上人家写的，然后一步步敲的，对一些html，css的基础知识，掌握也不是很好，可能就学的那两天印象比较深刻，现在可能部分都忘记了<br>
3）	对servlet、mysql、tomcat的实操还是比较多，掌握是比上面两个要好的，但是servlet里进行操作的一些方法太多了，可能进行的实操还是不够多，也是容易忘<br>
4）	数据库中的两个表，可以再加上主外键，这个操作当时没有实现<br>
5）	文件上传、文件下载这两个功能，网课上其实是有的，当时也是跟着网课做的，文件上传这个功能是加入了自己的功能，文件下载这个也只是把它看会了，并没有加入自己的东西，而且这两个功能还用了jar包里面已经写好的方法，只能说会用，但是肯定不如自己写出来的方法让印象更深刻<br>
6）	断点传续也是csdn复制的，然后实现原理大概看懂了，但是没有是自己写的，其实也相当于没有实现这个功能<br>
7）	在里面的一些小功能，是通过学习cookie和session来自己实现的<br>
8）	自己大概在这一个月时间就学习了这些知识点，感觉挺少的，现在来看，实现的功能也是很一般，总之还需努力<br>
9）	总之，对于作业布置的那些加分项，网课中没有设计，然后我买的书里面（当时是书买的不好，忘记看培养计划里的书了）也没有，但是csdn中都可以搜出来，可是真的没有时间和精力去研究，要期末了，也是，没有进行一些系统的学习和时间的积累，一些功能做不出来也正常，毕竟web一共也就学了一个月多一点，学习时间还是太少了<br>
10）以上功能虽然是做出来了，但是可能期末复习期间，这些知识都多多少少会忘掉一些<br>
