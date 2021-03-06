package com.hci.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static String courseTutoring;
    private static User targetUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavBarActivity(this, (DrawerLayout) findViewById(R.id.drawer_layout), navigationView));

        TextView navName = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView navEmail = navigationView.getHeaderView(0).findViewById(R.id.user_email);
        navEmail.setText(DummyAuth.getCurrentUser().getEmail());
        navName.setText(String.format("%s %s", DummyAuth.getCurrentUser().getFirst(), DummyAuth.getCurrentUser().getLast()));
        User currUser = isCurrentUserProfile()? DummyAuth.getCurrentUser() : targetUser;
        TextView fullName = findViewById(R.id.fullName);
        fullName.setText(String.format("%s %s", currUser.getFirst(), currUser.getLast()));
        TextView email = findViewById(R.id.userEmail);
        email.setText(currUser.getEmail());

//        final Intent messageIntent = new Intent(this, MessagesActivity.class);
//        messageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MessagesActivity.setUser(targetUser);
//                startActivity(messageIntent);
//
//            }
//        });

        ableToSend();

        showTutoringCourse();


    }

    public void goToChat(View view){
        final Intent messageIntent = new Intent(this, MessagesActivity.class);
        MessagesActivity.setUser(targetUser);
        startActivity(messageIntent);
    }

    private void showTutoringCourse() {
        LinearLayout courseTutor = findViewById(R.id.courseTutoring);
        if(TextUtils.isEmpty(courseTutoring) || isCurrentUserProfile()){
            courseTutoring = null;
            courseTutor.setVisibility(View.GONE);
        }else{
            TextView course = courseTutor.findViewById(R.id.course);
            course.setText(courseTutoring);
            courseTutor.setVisibility(View.VISIBLE);
        }
    }

    public static void setCourseTutoring(String course){
        courseTutoring = course;
    }

    public static void setTargetUser(User user){
        targetUser = user;
    }

    public boolean isCurrentUserProfile(){
        return targetUser == null? true :
                targetUser.getEmail().equals(DummyAuth.getCurrentUser().getEmail());
    }

    public void ableToSend(){
        Button messageButton = findViewById(R.id.button);
        if (isCurrentUserProfile()) {
            messageButton.setVisibility(View.GONE);
        } else {
            messageButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
