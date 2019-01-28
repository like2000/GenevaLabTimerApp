package ch.li.k.genevalabtimerapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ch.li.k.genevalabtimerapp.stats.StatsFragment;
import ch.li.k.genevalabtimerapp.stats.dummy.DummyContent;
import ch.li.k.genevalabtimerapp.tictoc.TicTocFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    transaction.replace(R.id.fragmentContainer, new TicTocFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
//                    TicTocFileStream ttModel = new TicTocFileStream(getApplicationContext());
//                    ttModel.check();
//                    try {
//                        ttModel.write();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    transaction.replace(R.id.fragmentContainer, new StatsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        System.out.println("Man!!!!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new TicTocFragment())
                .commit();
    }
}
