package game.challenge.mysquar.challengegame;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import game.challenge.mysquar.challengegame.view.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(WelcomeFragment.getInstance(), true, 0);
    }

    private  void addFragment(Fragment fragment, boolean addBacktoStack, int transition) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.setTransition(transition);
        if (addBacktoStack)
            ft.addToBackStack(null);
        ft.commit();
    }

    public void addFragment(Fragment fragment, boolean addBacktoStack) {
        addFragment(fragment, addBacktoStack, 0);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>1){
            super.onBackPressed();
        }else{
            finish();
        }
    }
}
