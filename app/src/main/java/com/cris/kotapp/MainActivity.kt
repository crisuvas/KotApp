package com.cris.kotapp
import Models.Person
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, SearchView.OnQueryTextListener {



    private var editName: EditText? = null
    private var editAge: EditText? = null
    //private var textName: TextView? = null
    //private var textAge: TextView? = null
    private var name = ""
    private var age = ""
    private var button: Button? =null
    private var radioM: RadioButton? = null
    private var radioF: RadioButton? = null
    private var listV: ListView? = null
    private var nombre: Array<String>? = null
    private var edad: Array<String>? = null
    private var sexo: Array<String>? = null
    private var gender = ""
    private var num = 10
    private var count = 1
    private var pos = 0
    private var action = "insert"
    private var listName: Array<String>? = null
    private var vibrator: Vibrator? = null
    private var search: SearchView? = null
    private var list: MutableList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editText_Name)
        editAge = findViewById(R.id.editText_Age)
        button = findViewById(R.id.button_ejecutar)
        radioM = findViewById(R.id.radioButton_M)
        radioF = findViewById(R.id.radioButton_F)
        listV = findViewById(R.id.list)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        nombre =Array(num, {""})
        edad =Array(num, {""})
        sexo =Array(num, {""})

        radioM!!.setOnClickListener(this)
        radioF!!.setOnClickListener(this)
        editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        button!!.setOnClickListener(this)
        editName!!.addTextChangedListener(this)
        editAge!!.addTextChangedListener(this)
        listV!!.onItemClickListener = this
        listV!!.onItemLongClickListener = this


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menusearch, menu)
        search = menu.findItem(R.id.app_bar_search).actionView as SearchView
        search!!.setOnQueryTextListener(this)
        return true
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button_ejecutar ->
                    operacion()
            R.id.radioButton_M ->{
                gender = "Masculino"
                Toast.makeText(this, "Ha seleccionado Masculino", Toast.LENGTH_SHORT).show()
            }

            R.id.radioButton_F ->{
                gender = "Femenino"
                Toast.makeText(this, "Ha seleccionado Femenino", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        name = editName?.text.toString()
        if(name == ""){
            editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        }else{
            editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
    }

    private fun operacion(){

        name = editName?.text.toString()
        age = editAge?.text.toString()

       /* if(name == ""){
            editName!!.requestFocus()
            /*textName?.text = ""
            if (age == "") {
                textAge?.text = ""
            } else {
                textAge?.text = age
            }*/
        }
        else {
           // textName?.text = name
           // if (age == "") {
                editAge!!.requestFocus()
               /* textAge?.text = ""
            } else {
                textAge?.text = age
            }*/
        }*/

       //TIPOS DE DATOS
        /*
        var cadena: String = ""
        var entero: Int = 0
        var dobles: Double = 0.0
        var valor: Boolean = false
        var char: Char = 'g'
        var flot: Float = 0.0F
        var intArray = IntArray(10)
        var doubleArray = DoubleArray(10)
        var charArray = CharArray(10)
        var booleanArray = BooleanArray(10)
        var floatArray = FloatArray(10)
        var byteArray = ByteArray(10)
        val array = arrayOf("Cris", "es", "guapo")
        array[1] = "muy guapo"
        for(s in array){
            cadena = s
        }
        for(i in 0..2){

        }
        var arrayS = Array<String?>(3){null}
        array.forEach{
            a -> cadena = a
        }
        var arraynull = arrayOfNulls<String>(3)
        arraynull.forEach{
            a -> cadena = a.toString()
        }
        */
        /*val nombre = "CRIS"
        if(true){
            entero = 5*5
        }else{
            cadena = "no tienes permiso"
        }
        val d = null?: 4
        var value = "Java"
        when(value){
            "Kotlin" -> message = "Hello"
            "Java" -> message = "World"
        }
        */

        //Inicialización con Arrays
        if(name == ""){
            editName!!.requestFocus()
        }else{
            if(age == ""){
                editAge!!.requestFocus()
            }else{
                if(radioM!!.isChecked || radioF!!.isChecked){
                    when(action){
                        "insert" ->
                                addDatos()
                        "update" ->
                                updateDatos()
                    }
                }
            }
        }
    }
    private fun addDatos(){
        list.add(Person(1, name, age.toInt(), gender ))
        watchData(list)
        editName!!.setText("")
        editAge!!.setText("")
    }
    private fun updateDatos(){
            nombre?.set(pos, name)
            edad?.set(pos, age)
            sexo?.set(pos, gender)
            listName = Array(count, { "" })
            var n = count - 1
            for (j in 0..n) {
                listName?.set(j, nombre?.get(j) as String)
            }
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
            listV!!.adapter = adapter
            editName!!.setText("")
            editAge!!.setText("")
            action = "insert"
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        editName!!.setText((list.get(position).getName()))
        editAge!!.setText((list.get(position).getAge().toString()))

        when(list.get(position).getType()){
            "Masculino" ->{
                radioM!!.isChecked = true
                gender = "Masculino"
            }
            "Femenino" ->{
                radioF!!.isChecked = true
                gender = "Femenino"
            }
        }
        pos = position
        action = "update"
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator?.vibrate(VibrationEffect.createOneShot(3,10))
            alertDialog(position)
        }else{
            vibrator?.vibrate(3)
            alertDialog(position)
        }
        return true
    }
    private fun alertDialog(i : Int){
        val alert = AlertDialog.Builder(this)
        alert.setIcon(R.mipmap.ic_tortuga)
                .setTitle(R.string.app_alertDialog)
                .setPositiveButton("Eliminar"){dialog, which ->
                    nombre?.set(i, "")
                    edad?.set(i, "")
                    sexo?.set(i, "")
                    mostrar()
                }
                .setNegativeButton("Cancelar"){dialog, which ->
                }
                .show()
    }
    private fun mostrar(){
        count = 1
        for(i in 0..num){
            if(nombre!![i] != ""){
                val listName = arrayOfNulls<String>(count)
                for(j in 0..count){
                    listName[j] = nombre!![i]
                }
            }
            count++
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
            listV!!.adapter = adapter
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        count = 1
        if(!newText.equals("", ignoreCase = true)){
            for(i in 0 until num){
                if(nombre!![i] != null){
                    if(nombre!![i].startsWith(newText.toString())){
                        val listName = arrayOfNulls<String>(count)
                        for(j in 0 until count){
                            listName[j] = nombre!![i]
                        }
                        count++
                        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
                        listV!!.adapter = adapter
                    }
                }
            }
        }else{
            count=1
            for(i in 0 until num){
                if(nombre!![i] != null){
                    val listName = arrayOfNulls<String>(count)
                    for(j in 0 until count){
                        listName[j] = nombre!![j]
                    }
                    count++
                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
                    listV!!.adapter = adapter
                }
            }
        }
        return false
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    private fun watchData(datalist: MutableList<Person>){
        val adapter = ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, datalist)
        listV!!.adapter = adapter
    }
}
