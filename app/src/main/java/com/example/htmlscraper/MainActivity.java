package com.example.htmlscraper;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.htmlscraper.databinding.ActivityMainBinding;

import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlPasswordInput;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });

        //starting in another thread cause getting network in main thread exception
        Thread thread = new Thread() {
            @Override
            public void run() {
                WebClient webClient = new WebClient();
                try {
                    System.out.println("getting page");
                    HtmlPage page = webClient.getPage("https://chat.ortoped.org.ru/test/test.php");
                    System.out.println("submitting form");
                    HtmlForm form = page.getFormByName("login_form");
                    HtmlTextInput loginField = form.getInputByName("login");
                    loginField.type("my login");
                    HtmlPasswordInput passwordField = form.getInputByName("password");
                    passwordField.type("my password");
                    HtmlSubmitInput button = form.getInputByName("submit");
                    HtmlPage resultsPage = button.click();
                    System.out.println("getting result");
                    DomElement element = resultsPage.getElementById("login");
                    System.out.println("login: " + element.getTextContent());
                    element = resultsPage.getElementById("password");
                    System.out.println("password: " + element.getTextContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}