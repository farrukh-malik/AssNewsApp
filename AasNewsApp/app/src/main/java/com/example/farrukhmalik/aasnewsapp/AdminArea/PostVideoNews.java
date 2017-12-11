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
import android.widget.Toast;
import android.widget.VideoView;

import com.example.farrukhmalik.aasnewsapp.AdminArea.SchemaPics.SchemaForFileUploading;
import com.example.farrukhmalik.aasnewsapp.R;
import com.example.farrukhmalik.aasnewsapp.StaticVariables;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostVideoNews extends Fragment {



    Button attachVideo;

    private int REQUEST_VIDEO= 2;

    View view;

    StorageReference firebaseStorage;
    private Uri uri = null;
    VideoView videoView;
    EditText et_title,et_description;
    String title, description;



    public PostVideoNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_post_video_news, container, false);


        firebaseStorage = FirebaseStorage.getInstance().getReference().child("Admin").child(StaticVariables.uid);
        videoView = (VideoView) view.findViewById(R.id.video);
        et_title = (EditText) view.findViewById(R.id.et_titleVideo);
        et_description = (EditText) view.findViewById(R.id.et_descriptionVideo);
        attachVideo = (Button) view.findViewById(R.id.attachVideoBtnId);
        attachVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_VIDEO);

            }
        });


        view.findViewById(R.id.uploadVideo).setOnClickListener(new View.OnClickListener() {
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

                        SchemaForFileUploading schemaForFileUploading = new SchemaForFileUploading(key,title,description,downloadUrl,2);

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

        if (requestCode == REQUEST_VIDEO && resultCode == Activity.RESULT_OK) {

            uri = data.getData();


            videoView.setVideoURI(uri);


        }


    }


}
