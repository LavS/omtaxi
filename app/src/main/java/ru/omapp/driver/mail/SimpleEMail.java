package ru.omapp.driver.mail;

import android.content.Intent;

import ru.omapp.driver.MainActivity;
import ru.omapp.driver.R;

public class SimpleEMail extends Object{

	public SimpleEMail(MainActivity main, String address, String subject, String emailtext) {

		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { address });
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);

		main.startActivity(Intent.createChooser(emailIntent, main.getResources().getString(R.string.sendmail)));
	}

}