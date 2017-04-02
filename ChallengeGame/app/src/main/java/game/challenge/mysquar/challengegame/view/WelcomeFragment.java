package game.challenge.mysquar.challengegame.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import game.challenge.mysquar.challengegame.MainActivity;
import game.challenge.mysquar.challengegame.R;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_welcome,null );
        initView(view);
        return view;
    }

    /**
     *
     * @param view view root.
     */
    private void initView(View view) {
        Button mBtnStart = (Button) view.findViewById(R.id.welcome_btn_start);
        Button mBtnExit = (Button) view.findViewById(R.id.welcome_btn_enxit);
        mBtnStart.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);
    }

    public static WelcomeFragment  getInstance(){
        return new WelcomeFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welcome_btn_start:
                addFragment(GameFragment.getInstance());
                break;
            case R.id.welcome_btn_enxit:
                exitGame();
                break;
            default:
                break;
        }
    }

    /**
     * add fragment
     * @param fragment fragment to add
     */
    private void addFragment(Fragment fragment){
        if(getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).addFragment(fragment,true);
        }
    }

    /**
     * Exit game
     */
    private void exitGame(){
        getActivity().onBackPressed();
    }
}
