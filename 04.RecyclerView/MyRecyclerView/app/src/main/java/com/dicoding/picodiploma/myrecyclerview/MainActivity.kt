package com.dicoding.picodiploma.myrecyclerview

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dicoding.picodiploma.myrecyclerview.adapter.CardViewHeroAdapter
import com.dicoding.picodiploma.myrecyclerview.adapter.GridHeroAdapter
import com.dicoding.picodiploma.myrecyclerview.adapter.ListHeroAdapter
import com.dicoding.picodiploma.myrecyclerview.model.Hero
import com.dicoding.picodiploma.myrecyclerview.model.HeroesData
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvCategory: RecyclerView
    private var list: ArrayList<Hero> = arrayListOf()
    private var mode: Int = 0
    private var title: String = "Mode List"

    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCategory = findViewById(R.id.rv_category)
        rvCategory.setHasFixedSize(true)

        /*
        Gunakanlah savedinstancestate untuk menjaga data ketika terjadi config changes
         */
        if (savedInstanceState == null) {
            /*
            Pada saat pertama kali activity dijalankan,
            Ambil data dari method getListData, kemudian tampilkan recyclerviewlist
             */
            setActionBarTitle(title)
            list.addAll(HeroesData.getListData())
            showRecyclerList()
            mode = R.id.action_list

        } else {
            /*
            Jika terjadi config changes maka ambil data yang dikirimkan dari saveinstancestate
             */
            title = savedInstanceState.getString(STATE_TITLE) as String
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST) as ArrayList
            val stateMode = savedInstanceState.getInt(STATE_MODE)

            /*
            Set data untuk title, list, dan mode yang dipilih
             */
            setActionBarTitle(title)
            list.addAll(stateList)
            setMode(stateMode)
        }

    }

    private fun showSelectedPresident(hero: Hero) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        rvCategory.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvCategory.adapter = listHeroAdapter

        listHeroAdapter.onItemClickListener = { hero ->
            showSelectedPresident(hero)
        }
    }

    private fun showRecyclerGrid() {
        rvCategory.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvCategory.adapter = gridHeroAdapter

        gridHeroAdapter.onItemClickListener = { hero ->
            showSelectedPresident(hero)
        }
    }

    private fun showRecyclerCardView() {
        rvCategory.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rvCategory.adapter = cardViewHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title= title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        setMode(item.itemId)

        return super.onOptionsItemSelected(item)
    }

    /*
    Method ini digunakan untuk seleksi mode yang dipilih
     */
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        /*
        Simpan jenis recyclerview yang sudah dipilih ke dalam variable mode
         */
        mode = selectedMode
        setActionBarTitle(title)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

}