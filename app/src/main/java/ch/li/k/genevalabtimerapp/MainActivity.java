package ch.li.k.genevalabtimerapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ch.li.k.genevalabtimerapp.cards.CardsFragment;
import ch.li.k.genevalabtimerapp.stats.StatsFragment;
import ch.li.k.genevalabtimerapp.tictoc.TicTocFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = item -> {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.navigation_home:
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
                transaction.replace(R.id.fragmentContainer, new StatsFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                return true;

            case R.id.navigation_notifications:
                transaction.replace(R.id.fragmentContainer, new CardsFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
        }
        return false;
    };

//    public void onListFragmentInteraction(DummyContent.DummyItem item) {
//        System.out.println("Man!!!!");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new TicTocFragment())
                .commit();
    }
}
