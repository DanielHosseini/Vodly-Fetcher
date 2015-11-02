package hosseini.daniel.vodlyfetcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2015-11-01.
 */
class MyAdapter extends BaseAdapter
{
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<String> MovieTitle, ArrayList<String> MovieCover)
    {
        inflater = LayoutInflater.from(context);
        for(int i = 0; i < MovieTitle.size(); i++){
            items.add(new Item(MovieTitle.get(i), MovieCover.get(i)));
        }
      // test Log.d("Movies", MovieTitle.get(3).toString());
      // test Log.d("Movies", MovieCover.toString());


    }
    public static Bitmap LoadImageFromWebOperations(String url) {

        try {
            Bitmap bmp = BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
            return bmp;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i)
    {
        return items.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = view;
        ImageView picture;
        TextView name;

        if(v == null)
        {

            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView)v.getTag(R.id.picture);
        name = (TextView)v.getTag(R.id.text);
        Item item = (Item)getItem(i);

        picture.setImageBitmap(LoadImageFromWebOperations(item.drawableId));
        name.setText(item.name);

        return v;
    }

    private class Item
    {
        final String name;
        final String drawableId;

        Item(String name, String drawableId)
        {
            this.name = name;
            this.drawableId = drawableId;
        }

    }
}

