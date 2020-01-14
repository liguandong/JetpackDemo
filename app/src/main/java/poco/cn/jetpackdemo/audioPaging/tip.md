# [构建自己的数据源](https://developer.android.google.cn/topic/libraries/architecture/paging/data#custom-data-source)
# [分页加载](https://https://www.jianshu.com/p/ff5c711bb7a1.android.google.cn/topic/libraries/architecture/paging/data#custom-data-source)

# [整合开发](https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..index#0#custom-data-source)


- 实现DataSource的一个子类
- 创建具体的DataSource.Factory

1.使用PageKeyDataSource，让你加载的页面插入到下一个或者以前的key,例如：例如：你要从网络获取社交媒体的帖子，你就需要通过nextPage加载到后续的加载中。

2.使用ItemKeyDataSource，如果你需要让使用的数据的item从N条增加到N+1条请使用。例如：你需要从嵌套评论中获取一条评论，需要通过一条评论的ID获取下一条评论的内容。

3.PositionalDataSource,如果你需要从事数据存储的任意位置来获取数据页，此类支持你从任意你选择的位置开始请求item的数据集。比如从第1200条返回20条item。


/**
 * PageKeyedDataSource分析说明，它的数据只会加载一次，来回滑动的话不会再次加载。
 * 我们平时如果分页用的是pageNum和pageSize的话用这个比较合适
 * */

ItemKeyedDataSource
这个加载数据的时候，如果是上拉的时候那么靠最后一条数据返回的key来决定，如果是下拉的话，是根据最顶上的一条数据来决定的
这个用来处理记载聊天记录最方便了。
默认加载数据库保存的最后比如10条记录展示，完事等同步完网络未读消息以后，
就可以根据最后一条数据的id，从数据库里查询之后的最新数据了。
如果我们往下拉，看历史数据，那么也可以跟具最顶上一条数据的id，从数据库里查比它更老的 数据拉。

PositionalDataSource
最后这种，就和他名字里的positional一样，它查询数据都是靠position的
而且，也可以看到，这个的类只有value一个泛型，如下，他的key已经固定是int拉
public abstract class PositionalDataSource<T> extends DataSource<Integer, T>
另外loadRange方法的参数LoadRangeParams的这个startPosition，这个最小是0
这个就是根据position来的，主要用来处理固定数量的数据，可以任意获取某段的数据。比如有100条数据，我可以从第20条数据开始获取。




https://www.jianshu.com/p/fd00c0fbd774