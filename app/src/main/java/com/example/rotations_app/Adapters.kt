package com.example.rotations_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rotations_app.Mitarbeiter.Mitarbeiter
import kotlinx.android.synthetic.main.activity_grund.view.*
import kotlinx.android.synthetic.main.items_row.view.*

class Adapters( val context: Context, val items:ArrayList<Mitarbeiter> ) : RecyclerView.Adapter<Adapters.ViewHolder>() {

        /**
         * Inflates the item views which is designed in xml layout file
         *
         * create a new
         * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.items_row,
                    parent,
                    false
                )
            )
        }

        /**
         * Binds each item in the ArrayList to a view
         *
         * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
         * an item.
         *
         * This new ViewHolder should be constructed with a new View that can represent the items
         * of the given type. You can either create a new View manually or inflate it from an XML
         * layout file.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = items.get(position)

            holder.tvId.text = item.id.toString()
            holder.tvName.text = item.name
            holder.tvPassword.text = item.password
            //holder.tvLevelK.text = item.levelK.toString()
            //holder.tvLevelG.text = item.levelG.toString()

            // Updating the background color according to the odd/even positions in list.
            if (position % 2 == 0) {
                holder.llMain.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.grayBackground
                    )
                )
            } else {
                holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }

            holder.ivEdit.setOnClickListener { view ->

                if (context is AddUser) {
                    context.updateRecordDialog(item)
                }
            }


            holder.ivDelete.setOnClickListener { view ->

                if (context is AddUser) {
                    context.deleteRecordAlertDialog(item)
                }
            }


        }

        /**
         * Gets the number of items in the list
         */
        override fun getItemCount(): Int {
            return items.size
        }

        /**
         * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            // Holds the TextView that will add each item to
            val llMain = view.llMain
            val tvId = view.tvId
            val tvName = view.tvName
            val tvPassword = view.tvPassword
            //val tvLevelK= view.tvLevelK
            //val tvLevelG = view.tvLevelG
            val ivEdit = view.ivEdit
            val ivDelete = view.ivDelete
        }
    }