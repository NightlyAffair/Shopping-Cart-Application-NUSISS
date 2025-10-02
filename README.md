Lifecycle :
02/10  Jayson finished basic entity design
@Data is the function of lombok. it will create getter and setter automatically, so you have no need to type in by yourself.
@Setter(AccessLevel.NONE) means tell the system not create the setter for this attribute. For example id is primary key and you can not modify by yourself. So no need to create setter().
