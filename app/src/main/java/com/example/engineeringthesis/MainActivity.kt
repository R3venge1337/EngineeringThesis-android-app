package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.engineeringthesis.utils.GlobalValues.currentAccount
import com.example.engineeringthesis.utils.GlobalValues.currentChild
import com.example.engineeringthesis.utils.GlobalValues.currentTeacher
import com.example.engineeringthesis.utils.UserNameDialog
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigationdrawer.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.username_dialog.*
import java.util.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(),UserNameDialog.UserNameDialogListener{

    lateinit var userNameDialog : UserNameDialog
    @Inject
    lateinit var sharedPreferences: SharedPreferences;
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;
    private var username:String = ""
    private var uniqueID:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = drawerlayout
        // obsługa przycisku wysuwającego menu
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        generateQuestUniqueId()
        //Toast.makeText(this,uniqueID, Toast.LENGTH_LONG).show()
        //Toast.makeText(this,currentChild?.accountChildId?.role?.roleName , Toast.LENGTH_LONG).show()
        //Toast.makeText(this, currentAccount?.role?.roleName, Toast.LENGTH_LONG).show()
        if(currentAccount?.role?.roleName.equals( "ROLE_dziecko"))
        {
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.navigation_drawer_menu_child_list);
            navigation_view.setNavigationItemSelectedListener { menuItem ->
                if(menuItem.itemId == R.id.childMyAccount)
                {
                    val childMyAccountActivity = Intent(this, ChildMyAccountActivity::class.java)
                    startActivity(childMyAccountActivity)
                }
                if(menuItem.itemId == R.id.childGameResults)
                {

                    val childResultsActivity = Intent(this,ChildResultsActivity::class.java)
                    startActivity(childResultsActivity)
                }
                if(menuItem.itemId == R.id.childrenGameResults)
                {
                    val childRankActivity = Intent(this, ChildButtRankActivity::class.java)
                    startActivity(childRankActivity)
                }
                if(menuItem.itemId == R.id.child_logout)
                {
                    currentAccount = null
                    currentChild = null
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                    Toasty.info(this, "Nastąpiło wylogowanie z aplikacji!" , Toast.LENGTH_LONG).show()
                }
                true
            }
        }
        else if(currentAccount?.role?.roleName == "ROLE_nauczyciel")
        {
            navigation_view.menu.clear();
            navigation_view.inflateMenu(R.menu.navigation_drawer_menu_teacher_list);
            navigation_view.setNavigationItemSelectedListener { menuItem ->
                if(menuItem.itemId == R.id.teacherMyAccount)
                {
                    val teacherMyAccountActivity = Intent(this, TeacherMyAccountActivity::class.java)
                    startActivity(teacherMyAccountActivity)
                }
                if(menuItem.itemId == R.id.teacherChildGameResults)
                {
                    //val registerActivity = Intent(this, RegisterActivitySelector::class.java)
                    //startActivity(registerActivity)
                }
                if(menuItem.itemId == R.id.teacherChildrenGameResults)
                {
                    val childRankActivity = Intent(this, ChildButtRankActivity::class.java)
                    startActivity(childRankActivity)
                }
                if(menuItem.itemId == R.id.teacherAddResources)
                {
                    val teacherAddResourcesActivity = Intent(this, TeacherAddResourcesActivity::class.java)
                    startActivity(teacherAddResourcesActivity)
                }
                if(menuItem.itemId == R.id.teacher_logout)
                {
                    currentAccount = null
                    currentTeacher = null
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                    Toasty.info(this, "Nastąpiło wylogowanie z aplikacji!" , Toast.LENGTH_LONG).show()
                }
                true
            }
        }
        else if(currentAccount?.role?.roleName == "ROLE_administrator")
        {
           // Toast.makeText(this, currentAccount?.role?.roleName , Toast.LENGTH_LONG).show()
            navigation_view.menu.clear();
            navigation_view.inflateMenu(R.menu.navigation_drawer_menu_admin_list);
            navigation_view.setNavigationItemSelectedListener { menuItem ->
                if(menuItem.itemId == R.id.AdminCreateTeacherAccount)
                {
                    val teacherRegisterActivity = Intent(this, TeacherRegisterActivity::class.java)
                    startActivity(teacherRegisterActivity)
                }
                if(menuItem.itemId == R.id.AdminDeleteAccounts)
                {
                    val adminDeleteAccountsActivity= Intent(this, AdminDeleteAccountsActivity::class.java)
                    startActivity(adminDeleteAccountsActivity)
                }
                if(menuItem.itemId == R.id.admin_logout)
                {
                    currentAccount = null
                    val mainActivity = Intent(this, MainActivity::class.java)
                    startActivity(mainActivity)
                    Toasty.info(this, "Nastąpiło wylogowanie z aplikacji!" , Toast.LENGTH_LONG).show()
                }
                true
            }
        }
        else if (currentAccount == null)
        {
            //Toast.makeText(this, "Konto jest  puste: " + currentAccount , Toast.LENGTH_LONG).show()
            navigation_view.getMenu().clear();
            navigation_view.inflateMenu(R.menu.navigation_drawer_menu_list)
            navigation_view.setNavigationItemSelectedListener { menuItem ->
                if(menuItem.itemId == R.id.signup)
                {
                    val loginActivity = Intent(this, LoginActivity::class.java)
                    startActivity(loginActivity)
                }

                if(menuItem.itemId == R.id.register)
                {
                    val registerActivity = Intent(this, ChildRegisterActivity::class.java)
                    startActivity(registerActivity)
                }
                true
            }
        }



        select_language_button.setOnClickListener {
            val selectLang = Intent(this@MainActivity, LanguageActivity::class.java)
            startActivity(selectLang)
        }
        username = sharedPreferences.getString("username","null").toString()
        if(username.equals(""))
        {
            openDialog()
        }

    }



    fun openDialog()
    {
        userNameDialog = UserNameDialog()
        userNameDialog.show(supportFragmentManager, "Dialog")
    }

    override fun yesClicked(message: String) {
        Toasty.info(this, "Nazwa użytkownika = " + message , Toast.LENGTH_LONG).show()
        sharedPreferencesEditor.putString("username",message).apply()
    }

    override fun noClicked(message: String) {
        Toasty.info(this, "Nazwa użytkownika = " + message, Toast.LENGTH_LONG).show()
    }

    fun  generateQuestUniqueId()
    {
        uniqueID = sharedPreferences.getString("questUniqueId","").toString()
        if(uniqueID.isNullOrEmpty())
        {
            uniqueID = UUID.randomUUID().toString();
            sharedPreferencesEditor.putString("questUniqueId",uniqueID).apply()
            //Toast.makeText(this,uniqueID, Toast.LENGTH_LONG).show()
        }
    }





}