package ie.ucd.cs.neilgrogan13204052.assignment2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import ie.ucd.cs.neilgrogan13204052.assignment2.model.Fruit;
import ie.ucd.cs.neilgrogan13204052.assignment2.model.Model;

public class MyAdapter extends ArrayAdapter<Model> {

		private final Context context;
		private final ArrayList<Model> modelsArrayList;

		public MyAdapter(Context context, ArrayList<Model> modelsArrayList) {
			 
			super(context, R.layout.list_item, modelsArrayList);
			
			this.context = context;
			this.modelsArrayList = modelsArrayList;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

            List<Fruit> fruitsToDisplay = modelsArrayList.get(position).getFruits();
		    
			// 1. Create inflater 
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = null;
            rowView = inflater.inflate(R.layout.list_item, parent, false);

            int count = 0;

            final ImageButton rightImageButton = (ImageButton) rowView.findViewById(R.id.fruit_image_button_right);

            for(Fruit f : fruitsToDisplay){
                if(count==0){
                    final ImageButton imgView = (ImageButton) rowView.findViewById(R.id.fruit_image_button_left);
                    TextView localNameText = (TextView) rowView.findViewById(R.id.fruit_name_local_left);
                    TextView resourceNameText = (TextView) rowView.findViewById(R.id.fruit_name_resource_left);
                    int imgId = context.getResources().getIdentifier(f.getIconName(), "drawable", context.getPackageName());
                    imgView.setImageResource(imgId);
                    int fruitNameId = context.getResources().getIdentifier(f.getName(), "string", context.getPackageName());
                    localNameText.setText(context.getResources().getString(fruitNameId));
                    resourceNameText.setText(f.getName());
                    rightImageButton.setVisibility(View.INVISIBLE);
                    count++;
                }else{
                    rightImageButton.setVisibility(View.VISIBLE);
                    final ImageButton imgView = (ImageButton) rowView.findViewById(R.id.fruit_image_button_right);
                    TextView localNameText = (TextView) rowView.findViewById(R.id.fruit_name_local_right);
                    TextView resourceNameText = (TextView) rowView.findViewById(R.id.fruit_name_resource_right);
                    int imgId = context.getResources().getIdentifier(f.getIconName(), "drawable", context.getPackageName());
                    imgView.setImageResource(imgId);
                    int fruitNameId = context.getResources().getIdentifier(f.getName(), "string", context.getPackageName());
                    localNameText.setText(context.getResources().getString(fruitNameId));
                    resourceNameText.setText(f.getName());
                }

            }

		    return rowView;
		}
}
