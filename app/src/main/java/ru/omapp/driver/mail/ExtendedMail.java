package ru.omapp.driver.mail;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import ru.omapp.driver.MainActivity;

public class ExtendedMail extends Object {

	int SELECTION = 3;

	String from = "omtaxi2018@gmail.com";
	String where;
	String title;
	String text;
	MainActivity main;
	
    /** Called when the activity is first created. */
    public ExtendedMail(MainActivity main, String address, String subject, String emailtext) {
    	this.main = main;
		this.where = address;
    	this.title = subject;
    	this.text = emailtext;
		sender_mail_async async_sending = new sender_mail_async();
		async_sending.execute();
    }
    
    private class sender_mail_async extends AsyncTask<Object, String, Boolean> {
    	ProgressDialog WaitingDialog;

		@Override
		protected void onPreExecute() {
			WaitingDialog = ProgressDialog.show(main, "Отправка данных", "Отправляем сообщение...", true);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			WaitingDialog.dismiss();
			Toast.makeText(main, "Регистрация завершена!", Toast.LENGTH_LONG).show();
		}

		@Override
		protected Boolean doInBackground(Object... params) {

			try {
                MailSenderClass sender = new MailSenderClass(from, "taxi2018");
                sender.sendMail(title, text, from, where, "");
			} catch (Exception e) {
				Toast.makeText(main, "Ошибка отправки сообщения!", Toast.LENGTH_LONG).show();
			}
			
			return false;
		}
	}
}