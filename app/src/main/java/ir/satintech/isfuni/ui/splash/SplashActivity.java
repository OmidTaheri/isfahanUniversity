package ir.satintech.isfuni.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.ui.main.MainActivity;
import ir.satintech.isfuni.utils.MyTransitionGenerator;

public class SplashActivity extends BaseActivity implements SplashMvpView {


    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @BindView(R.id.main_progress)
    ProgressBar mainProgress;
    @BindView(R.id.errore_text)
    TextView erroreText;
    @BindView(R.id.error_btn_retry)
    Button errorBtnRetry;
    @BindView(R.id.error_layout)
    ConstraintLayout errorLayout;
    @BindView(R.id.kenburnsview)
    KenBurnsView kenburnsview;



    public static Intent getStartIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);

        setUp();

    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();

    }


    @Override
    protected void setUp() {

        /////////////
        LinearInterpolator linearinterpolator = new LinearInterpolator();
        MyTransitionGenerator generator = new MyTransitionGenerator(3000, linearinterpolator);
        kenburnsview.setTransitionGenerator(generator);


        ////////////
        mainProgress.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        mPresenter.delayToNextActivity(this);
    }



    @Override
    public void launchMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorLayout() {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            mainProgress.setVisibility(View.GONE);

            erroreText.setText(R.string.error_connection);

        }

        errorBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errorLayout.setVisibility(View.GONE);
                mainProgress.setVisibility(View.VISIBLE);

                mPresenter.delayToNextActivity(SplashActivity.this);


            }
        });
    }




}
