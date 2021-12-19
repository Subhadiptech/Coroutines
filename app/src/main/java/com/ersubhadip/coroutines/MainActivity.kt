package com.ersubhadip.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Coroutines
        GlobalScope.launch {
            //this is a basic launch for coroutines but not the best method

        }

        //to specify in which type of thread coroutine will run depends upon our use case

        GlobalScope.launch(Dispatchers.Main) {

            //Runs on main thread - UI Operation
        }

        GlobalScope.launch(Dispatchers.IO) {
            //Best for CRUD, Network Calls and Read/Write files
        }

        GlobalScope.launch(Dispatchers.Default) {
            //Complex Calc
        }

        GlobalScope.launch(Dispatchers.Unconfined) {
            //Not confined to specific threads
        }


        //Global Scope means this thread will keep running until application is closed but using this is not a good practice as it can create memory leakage

        //Other scopes which are lifecycle aware ViewModelScope and LifecycleScope

        //One of the adv of coroutines is that it can switch context (threads in this case) while its thread is running
        //Example Code

        GlobalScope.launch(Dispatchers.IO) {

            //fetch on IO thread and set data on main thread

            //Network call
            delay(3000L)
            Log.d(TAG, "Data Fetched")

            //now we have to set the data

            withContext(Dispatchers.Main) {
                //Switched to main thread
                delay(2000L)
                Log.d(TAG, "Setting Data Successful")
            }

        }

        //suspend function in Kotlin:
        /*
        This is a keyword used to make suspendable function
        which can run only within another suspend function
        or inside coroutine block.
         */

        GlobalScope.launch(Dispatchers.Default) {
            networkCall()

        }

        // runBlocking :
        /*
        This is a block also helps us to do async activities but this actually blocks the main thread. So why to use them ?
        -> 1: We need to call some suspend func like delay() synchronously  2: Junit Testing
         */

        runBlocking {
            //This also launches Coroutines
            delay(5000L)

        }

    }

    suspend fun networkCall(): String {
        delay(5000L)     //delay() is also a suspendable func so it cannot be used anywhere outside
        return "Fetched Data"
    }
}