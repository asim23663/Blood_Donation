package com.example.asimyamin.blood_donation.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.example.asimyamin.blood_donation.MyApp
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    //2020-05-09
    private fun getDate(ct: Context, etDate: EditText) {
        val myCalendar = Calendar.getInstance()
        val date = OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            // TODO Auto-generated method stub
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = monthOfYear
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            updateLabel(etDate, myCalendar)
        }
        DatePickerDialog(ct, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]).show()
    }

    private fun updateLabel(et: EditText, myCalendar: Calendar) {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
        et.setText(sdf.format(myCalendar.time))
    }

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(date: String?): String? {
//        String date="Mar 10, 2016 6:30:00 PM";
        var date = date
        @SuppressLint("SimpleDateFormat") var spf = SimpleDateFormat(DATE_FORMAT)
        val newDate: Date?
        return try {
            newDate = spf.parse(date)
            spf = SimpleDateFormat("dd MMM yyyy")
            assert(newDate != null)
            date = spf.format(newDate)
            date
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }

    fun goToNextScreenWithFinish(cls: Class<*>?, ct: Context) {
        val intent = Intent(ct, cls)
        ct.startActivity(intent)
        (ct as Activity).finish()
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun goToNextScreenWithoutFinish(cls: Class<*>?, ct: Context) {
        val intent = Intent(ct, cls)
        ct.startActivity(intent)
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
        @SuppressLint("UseCompatLoadingForDrawables")
        fun setImage(imgUrl: String?, imageView: ImageView?) {

            /*Picasso.get()
                .load(imgUrl)
                .placeholder(MyApp.getContext().getResources().getDrawable(R.drawable.place_holder, null))
                .error(MyApp.getContext().getResources().getDrawable(R.drawable.place_holder, null))
                .into(imageView);*/
            /*  ImageLoader imageLoader = Coil.imageLoader(MyApp.getContext());
        @SuppressLint("UseCompatLoadingForDrawables")
        ImageRequest request = new ImageRequest.Builder(MyApp.getContext())
                .data(imageName)
//                .crossfade(500)
                .placeholder(MyApp.getContext().getResources().getDrawable(R.drawable.place_holder, null))
                .error(MyApp.getContext().getResources().getDrawable(R.drawable.place_holder, null))
                .target(imageView)
                .build();
        imageLoader.enqueue(request);*/
        }

        fun roundDecimal(d: Float, decimalPlace: Int): BigDecimal {
            var bd = BigDecimal(java.lang.Float.toString(d))
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
            return bd
        }

        /*public static void setProfilePhoto(String imageUrl, ImageView imageView) {
        ImageLoader imageLoader = Coil.imageLoader(MyApp.getContext());
        @SuppressLint("UseCompatLoadingForDrawables")
        ImageRequest request = new ImageRequest.Builder(MyApp.getContext())
                .data(imageUrl)
//                .crossfade(500)
                .placeholder(MyApp.getContext().getResources().getDrawable(R.drawable.profile, null))
                .error(MyApp.getContext().getResources().getDrawable(R.drawable.profile, null))
                .target(imageView)
                .build();
        imageLoader.enqueue(request);
    }*/
        fun changeStatusBarColor(statusBarColor: Int, myActivityReference: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = myActivityReference.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = statusBarColor
            }
        }

        fun navigateAndClearBackStack(cls: Class<*>?, ct: Context) {
            val intent = Intent(ct, cls)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            ct.startActivity(intent)
            (ct as Activity).finish()
        }

        fun showSnackBar(view: View?, msg: String?) {
            //findViewById(android.R.id.content)
            Snackbar.make(view!!, msg!!, Snackbar.LENGTH_SHORT)
                    .show()
        }

        fun setVisibility(visible: View, gone: View) {
            visible.visibility = View.VISIBLE
            gone.visibility = View.GONE
        }

        fun showToast(msg: String?) {
            Toast.makeText(MyApp.context, msg, Toast.LENGTH_LONG).show()
        }

        fun showShortToast(msg: String?) {
            Toast.makeText(MyApp.context, msg, Toast.LENGTH_SHORT).show()
        }

        /*public static void showCustomToastLayout(Context ct, String msg) {
        CustomToastBinding customToastBinding = CustomToastBinding.inflate(LayoutInflater.from(ct));
        customToastBinding.text.setText(msg);

        Toast toast = new Toast(ct);
//        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(customToastBinding.getRoot());
        toast.show();

    }
*/


        /*public static boolean isEmpty(TextInputEditText et) {

        return TextUtils.isEmpty(getText(et).trim());
    }*/
        fun isEmpty(tv: TextView): Boolean {
            return TextUtils.isEmpty(getText(tv).trim { it <= ' ' })
        }

        fun isEmpty(et: EditText): Boolean {
            return TextUtils.isEmpty(getText(et))
        }

        fun getText(et: EditText): String {
            return et.text.toString().trim { it <= ' ' }
        }

        fun getText(tv: TextView): String {
            return tv.text.toString().trim { it <= ' ' }
        }

        fun isInternetAvailable(): Boolean {
            var result = false
            val connectivityManager =
                    MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }

            return result
        }


    }
}