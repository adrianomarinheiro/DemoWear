package br.com.adrianomarinheiro.demowear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Toast
import br.com.adrianomarinheiro.shared.Carro
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity(),
        GoogleApiClient.ConnectionCallbacks{

    private lateinit var client: GoogleApiClient

    override fun onConnected(p0: Bundle?) {
        Wearable.MessageApi.addListener(client) {
            val carro = Gson()
                    .fromJson(String(it.data), Carro::class.java)
            Toast.makeText(this, carro.modelo,
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        client = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build()
        client.connect()

        btClicAqui.setOnClickListener {
            Toast.makeText(this, "Fui Clicado", Toast.LENGTH_LONG).show()
        }
    }
}
