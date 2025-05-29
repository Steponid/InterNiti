package com.example.interniti.ui.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interniti.R
import com.example.interniti.databinding.FragmentAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.interniti.ui.admin.UserAdapter as UserAdapter1

class AdminFragment : Fragment() {

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    lateinit var add: ImageView

    lateinit var adapter: UserAdapter1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val root = binding.root

        recyclerView = root.findViewById(R.id.admin_recycler)
        add = root.findViewById(R.id.add_user)

        add.setOnClickListener {
            startActivity(Intent(requireContext(), Add_new_userActivity::class.java))
        }

        adapter = UserAdapter1()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadUsersFromFirebase()

        return root
    }



    private fun loadUsersFromFirebase() {
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email ?: run {
            Toast.makeText(context, "Пользователь не авторизован", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseFirestore.getInstance().collection("Users")
            .whereNotEqualTo("mail", currentUserEmail)
            .get()
            .addOnSuccessListener { result ->
                val users = result.toObjects(UserItem::class.java)
                Log.d("AdminFragment", "Загружено пользователей: ${users.size}")
                adapter.submitList(users)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Ошибка загрузки пользователей", Toast.LENGTH_SHORT).show()
                Log.e("AdminFragment", "Ошибка Firestore: $exception")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}