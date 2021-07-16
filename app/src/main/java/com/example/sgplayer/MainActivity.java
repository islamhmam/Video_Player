package com.example.sgplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {
RecyclerView recyclerView;
VideoAdapter videoAdapter;
File path=new File(System.getenv("EXTERNAL_STORAGE"));
List<File> filesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermission();

    }

    private void askPermission() {

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    displayFiles();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void displayFiles() {
recyclerView=findViewById(R.id.main_rv);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new GridLayoutManager(this,2));
filesList=new ArrayList<>();
filesList.addAll(findVideos(path));
//filesList.addAll(findVideos(Environment.getDataDirectory()));
videoAdapter=new VideoAdapter(this,filesList,this);

recyclerView.setAdapter(videoAdapter);

    }

    private ArrayList<File> findVideos(File file){
        ArrayList<File> myVideos =new ArrayList<>();

        File[] allFiles=file.listFiles();

        for (File singleFile : allFiles){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                myVideos.addAll(findVideos(singleFile));

            }else if (singleFile.getName().toLowerCase().endsWith(".mp4")){
                myVideos.add(singleFile);
            }

        }
return myVideos;

    }

    @Override
    public void onSelectListener(File file) {
    startActivity(new Intent(MainActivity.this,DisplayActivity.class)
    .putExtra("videoPath",file.getAbsolutePath()));
    }
}