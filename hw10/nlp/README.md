张吉祺 16340286

基于课程学习内容，实现简单的搜索引擎功能(界面可视化不做要求，可终端输出)，实现以下基本功能:
1. 拼写检查（参考最小编辑距离原理）

    利用`org.apache.lucene.search.spell.SpellChecker`，并建立字典索引和相似度计算。[Search.java](src/retrieval/Search.java)
2. 倒排索引

    利用`org.apache.lucene.document.Field`。[Indexer.java](src/retrieval/Indexer.java)
3. 使用TF/IDF或者VSM进行文档排序

    利用`org.apache.lucene.search.similarities.ClassicSimilarity`。[Searcher.java](src/retrieval/Searcher.java)

首先运行[CreateIndex.java](src/retrieval/CreateIndex.java)建立倒排索引。
```
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/29.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/15.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/114.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/100.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/128.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/129.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/101.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/115.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/14.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/28.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/16.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/103.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/117.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/116.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/102.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/17.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/13.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/106.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/112.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/113.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/107.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/12.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/10.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/38.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/139.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/111.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/105.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/104.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/110.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/138.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/39.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/11.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/76.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/188.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/62.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/89.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/177.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/163.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/162.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/88.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/176.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/63.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/77.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/189.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/49.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/61.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/75.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/160.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/174.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/148.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/149.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/175.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/161.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/74.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/60.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/48.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/64.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/70.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/58.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/159.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/165.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/171.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/170.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/164.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/158.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/59.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/71.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/65.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/73.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/67.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/9.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/172.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/98.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/166.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/99.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/167.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/173.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/8.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/66.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/72.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/181.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/57.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/5.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/43.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/156.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/142.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/94.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/80.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/81.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/95.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/143.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/157.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/42.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/56.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/4.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/180.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/182.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/68.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/40.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/6.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/54.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/141.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/155.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/83.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/169.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/97.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/168.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/96.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/82.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/154.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/140.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/7.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/55.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/41.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/69.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/183.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/45.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/51.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/3.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/187.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/79.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/193.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/178.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/86.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/92.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/144.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/150.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/151.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/145.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/93.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/179.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/87.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/192.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/186.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/78.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/50.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/2.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/44.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/52.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/46.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/190.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/184.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/91.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/85.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/153.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/147.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/146.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/152.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/84.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/90.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/185.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/191.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/47.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/1.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/53.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/34.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/20.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/135.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/121.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/109.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/108.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/120.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/134.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/21.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/35.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/23.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/37.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/122.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/136.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/137.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/123.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/36.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/22.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/26.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/32.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/127.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/133.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/132.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/126.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/33.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/27.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/31.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/25.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/19.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/118.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/130.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/124.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/125.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/131.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/119.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/18.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/24.txt
Indexing /Users/zjq/remote/eclipse-workspace/nlp/data/page/30.txt
193 File indexed, time taken: 2982 ms
```
然后运行[Search.java](src/retrieval/Search.java)输入检索，可以看到拼写检查。
```
Search:
外科医院
Did you mean:
[0] 中科院
[1] 妇产医院
[2] 社科院
[3] 科学院
Input digit AND/OR press enter to continue:

Search 外科医院
27 hits documents found. Time :34830
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/187.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/155.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/28.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/34.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/62.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/98.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/128.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/2.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/83.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/57.txt
Search:
外科医院
Did you mean:
[0] 中科院
[1] 妇产医院
[2] 社科院
[3] 科学院
Input digit AND/OR press enter to continue:
0
Search 中科院
14 hits documents found. Time :18776
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/91.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/68.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/71.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/57.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/185.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/128.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/77.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/93.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/121.txt
File: /Users/zjq/remote/eclipse-workspace/nlp/data/page/111.txt
Search:
```

> 参考  
> https://www.yiibai.com/lucene/lucene_first_application.html  
> https://rickerg.com/