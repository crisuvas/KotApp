package Models

class Person{
    private var id = 0
    private var name = ""
    private var age = 0
    private var type = ""

    constructor(id: Int, name: String, age: Int, type: String) {
        this.id = id
        this.name = name
        this.age = age
        this.type = type
    }
    fun getId(): Int{
        return id
    }
    fun getName(): String{
        return name
    }
    fun getAge(): Int{
        return age
    }
    fun getType(): String{
        return type
    }

    override fun toString(): String {
        return name
    }

}