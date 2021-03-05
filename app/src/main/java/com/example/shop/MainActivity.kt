package com.example.shop

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var names = R.array.guitarName
    var price = R.array.price
    var pics = R.array.pics
    var quantity = 1
    private val modelsList = arrayListOf<Model>()
    private val modelsName = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val minusButton = findViewById<Button>(R.id.MinusButton)
        val quantityNumber = findViewById<TextView>(R.id.QuantityNumber)
        minusButton.isEnabled = quantityNumber.text.toString().toInt() != 0
        val spinnerChoiceGuitar = findViewById<Spinner>(R.id.spinnerChoiceGuitar)

        modelsList.add(Model("Belucci BC3820 WH", 3099, getDrawable(R.drawable.akusticheskaya_gitara_belucci_bc3820_wh_1200392_1) as Drawable))
        modelsList.add(Model("YAMAHA C40 Black", 12542, getDrawable(R.drawable.yamaha_c40bl) as Drawable))
        modelsList.add(Model("Flight F-230C WR", 8520, getDrawable(R.drawable.gitarablyat) as Drawable))
        modelsList.add(Model("Xiaomi Kickgoods Populele 2 Black", 9990, getDrawable(R.drawable.populele2_blackc) as Drawable))
        modelsList.forEach{
            modelsName.add(it.name)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modelsName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerChoiceGuitar.adapter = adapter

        spinnerChoiceGuitar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {

            override fun onNothingSelected(p0: AdapterView<*>?)
            {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val textViev = p1 as TextView
                val image = findViewById<ImageView>(R.id.imageView)
                val priceValue = findViewById<TextView>(R.id.GuitarCost)
                var priceSumm = findViewById<TextView>(R.id.PriceSumm)
                var quantity = findViewById<TextView>(R.id.QuantityNumber)
                image.setImageDrawable(modelsList.first{it.name == textViev.text}.pic)
                priceValue.text = modelsList.first { it.name == textViev.text }.price.toString()
                priceSumm.text = (priceSumm.text.toString().toInt() * 0).toString()
                quantity.text = (quantity.text.toString().toInt() * 0).toString()
            }

        }
    }

    data class Model (val name: String, var price: Int, var pic: Drawable)
    {

    }
    fun IncreaseNumber(view: View)
    {
        val minusButton = findViewById<Button>(R.id.MinusButton)
        val quantityNumber = findViewById<TextView>(R.id.QuantityNumber)
        val number = quantityNumber.text.toString().toInt()
        val priceSumm = findViewById<TextView>(R.id.PriceSumm)
        val guitarCost = findViewById<TextView>(R.id.GuitarCost)
        quantityNumber.text = (number +quantity).toString()
        priceSumm.text = (guitarCost.text.toString().toInt() * quantityNumber.text.toString().toInt()).toString()
        minusButton.isEnabled = quantityNumber.text.toString().toInt() != 0
    }
    fun DecreaseNumber(view: View)
    {
        val minusButton = findViewById<Button>(R.id.MinusButton)
        val quantityNumber = findViewById<TextView>(R.id.QuantityNumber)
        val number = quantityNumber.text.toString()
        val priceSumm = findViewById<TextView>(R.id.PriceSumm)
        val guitarCost = findViewById<TextView>(R.id.GuitarCost)
        quantityNumber.text = (number.toInt() -quantity).toString()
        if(priceSumm.text.toString().toInt() < guitarCost.text.toString().toInt())
        {
            priceSumm.text = (priceSumm.text.toString().toInt() * 0).toString()
        }
        else
        {
            priceSumm.text = (priceSumm.text.toString().toInt() - guitarCost.text.toString().toInt()).toString()
        }
        minusButton.isEnabled = quantityNumber.text.toString().toInt() != 0
    }
    fun basket(view: View)
    {
        val intent = Intent(this@MainActivity, Basket::class.java)
        startActivity(intent)
       // var intent = Intent(this, Basket::class.java)
    }

}