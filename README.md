# Lifecycle :
## V1.001
### 02/10 15:03 
### Modified by : Jayson 
### finished basic entity design without relationship and foreign key
### @Data is the function of lombok. it will create getter and setter automatically, so you have no need to type in by yourself.
### @Setter(AccessLevel.NONE) means tell the system not create the setter for this attribute. For example id is primary key and you can not modify by yourself. So no need to create setter().
### @IdClass is used for composite key. Here We have composite key. If just using @Id may have problem. I am not very sure with that
#### The @IdClass annotation in Java Persistence API (JPA) is used to define a composite primary key for an entity. A composite primary key consists of multiple fields that together uniquely identify an entity. The @IdClass annotation specifies a separate class to represent this composite key.