package dziejesie.app.shoppinglist.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.adapters.ProductAdapter
import dziejesie.app.shoppinglist.adapters.ProductAdapterActive
import dziejesie.app.shoppinglist.listeners.ClickListener
import dziejesie.app.shoppinglist.models.Product
import dziejesie.app.shoppinglist.models.ShoppingList
import dziejesie.app.shoppinglist.transporters.ListTransporter
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListDetails : Fragment(), ClickListener {

    private val database = Firebase.firestore
    private val collection = database.collection("ShoppingLists")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_list_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar_list_details)
            .setupWithNavController(navController, appBarConfiguration)
        recyclerView(view)
    }

    private fun recyclerView(view: View){
        val list: ShoppingList = ListTransporter.getList()
        val adapter = ProductAdapterActive(list.productList, this)
        if(!list.archived){
            view.findViewById<FloatingActionButton>(R.id.floatingActionButtonProducts).setOnClickListener {
                val product = Product("Some Grocery", 120.0, 10)
                list.productList.add(product)
                collection.document(ListTransporter.getSnapshot().id).update("productList", list.productList)
                adapter.update()
            }
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.products)
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onClick(pos: Int) {
        TODO("Not yet implemented")
    }


}