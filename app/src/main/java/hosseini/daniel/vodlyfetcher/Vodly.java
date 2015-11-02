package hosseini.daniel.vodlyfetcher;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Vodly extends ActionBarActivity {
    public  ArrayList<String> featuredMovies = new ArrayList<String>();
    public static ArrayList<String> featuredMoviesCoverImage = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vodly);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Document htmlDocument = Jsoup.connect("http://vodly.to/index.php?sort=featured").maxBodySize(0).timeout(600000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
              .get();
            Elements links = htmlDocument.select("div.index_item");

            for (Element link : links) {
                featuredMovies.add(link.getElementsByAttribute("title").text());
                featuredMoviesCoverImage.add(link.select("img").attr("src"));
            }
            GridView gridView = (GridView)findViewById(R.id.gridview);
            gridView.setAdapter(new MyAdapter(this,featuredMovies,featuredMoviesCoverImage));
           /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                {
                    // this 'mActivity' parameter is Activity object, you can send the current activity.
                    Log.d("MYINT", "value: " + position);
                    Intent intent = new Intent(Vodly.this, InfoMovie.class);
                    startActivity(intent);
                }
            }); */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
