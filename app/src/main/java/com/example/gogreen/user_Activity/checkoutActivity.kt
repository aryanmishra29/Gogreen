package com.example.gogreen.user_Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gogreen.R
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class checkoutActivity : AppCompatActivity() , PaymentResultListener {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val checkout = Checkout()
      // checkout.setKeyID( "SzzmGYd2srMwXMMYTX0K")
        checkout.setKeyID("rzp_test_YMpRNrRlG6VDeZ")
        try {
            val options = JSONObject()
            options.put("name", "GoGreen")
            options.put("description", "Best Ecommerce App")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#673AB7")
            options.put("currency", "BDT")
          //  options.put("amount", (price!!.toInt() * 100)) //pass amount in currency subunits
            options.put("prefill.email", "bhoomiag67890@gmail.com.com")
            options.put("prefill.contact", "+9084243046")
            checkout.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }


    }

     override fun onPaymentSuccess(p0:String?) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()


    }

    override fun onPaymentError(p0:Int, p1:String?) {
        Toast.makeText(this, "Payment error", Toast.LENGTH_SHORT).show()

    }
}
