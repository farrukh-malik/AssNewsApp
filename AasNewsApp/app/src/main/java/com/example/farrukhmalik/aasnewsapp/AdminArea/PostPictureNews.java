package com.example.farrukhmalik.aasnewsapp.AdminArea;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farrukhmalik.aasnewsapp.AdminArea.SchemaPics.SchemaForFileUploading;
import com.example.farrukhmalik.aasnewsapp.CustomListAdapter;
import com.example.farrukhmalik.aasnewsapp.R;
import com.example.farrukhmalik.aasnewsapp.StaticVariables;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostPictureNews extends Fragment {


    Button attachPic;

    private int REQUEST_IMAGE_CAPTURE = 1;

    View view;

    ArrayList<SchemaForFileUploading> list;

    CustomListAdapter customListAdapter;

    StorageReference firebaseStorage;
    private Uri uri = null;
    ImageView imageView;
    EditText et_title,et_description;
    String title, description;
    ListView listView;
    public PostPictureNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_post_picture_news, container, false);

        listView = (ListView) view.findViewById(R.id.pictureList);

        list = new ArrayList<>();

        customListAdapter = new CustomListAdapter(getContext(),list);

        listView.setAdapter(customListAdapter);

        FirebaseDatabase.getInstance().getReference().child("Admin").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                SchemaForFileUploading schemaForFileUploading = dataSnapshot.getValue(SchemaForFileUploading.class);



                list.add(schemaForFileUploading);

                customListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseStorage = FirebaseStorage.getInstance().getReference().child("Admin").child(StaticVariables.uid);
        imageView = (ImageView) view.findViewById(R.id.image);
        et_title = (EditText) view.findViewById(R.id.et_title);
        et_description = (EditText) view.findViewById(R.id.et_description);
        attachPic = (Button) view.findViewById(R.id.attachPicBtnId);
        attachPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_CAPTURE);

            }
        });



        view.findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = et_title.getText().toString().trim();

                description = et_description.getText().toString().trim();

                if(uri == null ){

                    //make message

                    return;

                }

                if(TextUtils.isEmpty(title)){

                    //make message

                    return;

                }

                if(TextUtils.isEmpty(description)){

                    //make message

                    return;

                }

                final String key = FirebaseDatabase.getInstance().getReference().child("Admin").push().getKey();


                firebaseStorage.child(key).putFile(uri).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String downloadUrl = taskSnapshot.getDownloadUrl().toString();

                        SchemaForFileUploading schemaForFileUploading = new SchemaForFileUploading(key,title,description,downloadUrl,1);

                        FirebaseDatabase.getInstance().getReference().child("Admin").child(key).setValue(schemaForFileUploading);

                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });



        return view;


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            uri = data.getData();


            imageView.setImageURI(uri);


        }


    }


}
