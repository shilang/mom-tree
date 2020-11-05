# mom-tree
以 type-tree.excel 为样本，在数据库oracle中存储
目前，构建出一棵树出来，以便后期快速查找类型的编码
思路：
   因为在type-tree.excel中，有type_code代表了一定的层级入父子关系。
   但用户查找时，却是以中文字来查询，所以SparePartsTypeService中的BuildTree()作用就是构建此树
 
 中间用了归递方法，
 数据结构： 当前结点node,
           parent
           children
           
当树型构建完成之后， 以query-keyword中的每条记录去匹配这棵树，而获取应的TYPE_CODE.
