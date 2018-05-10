package tdc.edu.vn.shoesshop.Khanh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.PromotionExpandableListAdapter;
import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EditingPromotionDetail;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class Promotions extends Activity implements SearchView.OnQueryTextListener {

    ExpandableListView lvPromotions;
    SearchView svSearchPromotions;
    ImageButton btnAdd, btnBack;
    HashMap<Promotion,ArrayList<PromotionsDetail>> list = new HashMap<>();
    public static ArrayList<Promotion> listParent = new ArrayList<>();
    ArrayList<Promotion> listCopy = new ArrayList<>();
    ArrayList<PromotionsDetail> promotionsDetailArrayList = new ArrayList<>();
    Promotion pro = new Promotion();

    // Write data to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public static PromotionExpandableListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotions_activity);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnAdd = (ImageButton)findViewById(R.id.btnAddPromotions);
        lvPromotions = (ExpandableListView) findViewById(R.id.lvListPromotions);
        svSearchPromotions = (SearchView) findViewById(R.id.svSearch);
        svSearchPromotions.setOnQueryTextListener(this);

        Query allPromtions = myRef.child("Promotions").orderByChild("shop").equalTo(user.getUid());
        listParent.clear();
        list.clear();
        listCopy.clear();

        promotionsDetailArrayList.clear();

       myRef.child("PromotionsDetails").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               PromotionsDetail promotionsDetail = dataSnapshot.getValue(PromotionsDetail.class);
               promotionsDetailArrayList.add(promotionsDetail);
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


        allPromtions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Promotion por = dataSnapshot.getValue(Promotion.class);
                listParent.add(por);
                listCopy.add(por);
                list.put(por, null);

                Log.d("key", listParent.size() + "");
                if(promotionsDetailArrayList.size() > 0) {
                    ArrayList<PromotionsDetail> arr = new ArrayList<>();
                        for (PromotionsDetail p : promotionsDetailArrayList) {
                            if (p.getPromotions().equals(por.getId())) {
                                arr.add(p);
                            }
                        }
                    list.put(por, arr);
                    adapter.notifyDataSetChanged();
                }
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

        adapter = new PromotionExpandableListAdapter(Promotions.this, listCopy, list);
        lvPromotions.setAdapter(adapter);

        intent = getIntent();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Promotions.this, HomeForShop.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(Promotions.this, EdittingPromotions.class);
                startActivity(intent);
            }
        });

        //dang ky contextmenu cho list view
        registerForContextMenu(lvPromotions);

    }

    //khoi tao contextmenu
    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

            PromotionsDetail item = (PromotionsDetail) adapter.getChild(group, child);
            menu.setHeaderTitle("Chọn để thực hiện");

            menu.add(0, R.id.cmSua, 0, "Sua");
            menu.add(0, R.id.cmXoa, 0, "Xoa");
        }
    }

    //chọn item của context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        final int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        final int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            final PromotionsDetail promotionsDetail = (PromotionsDetail) adapter.getChild(group, child);
            switch (item.getItemId()) {
                case R.id.cmSua:
                    //Toast.makeText(Promotions.this, "sua" + promotionsDetail.getProduct(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Promotions.this, EditingPromotionDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", promotionsDetail);
                    intent.putExtra("sua", bundle);
                    startActivity(intent);
                    break;

                case R.id.cmXoa:
                    //Toast.makeText(Promotions.this, "xoa" + promotionsDetail.getProduct(), Toast.LENGTH_SHORT).show();

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Promotions.this);
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setIcon(R.mipmap.ic_launcher);
                    alertDialog.setMessage("Bạn muốn xóa chi tiết khuyến mãi này?");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            list.get(listParent.get(group)).remove(child);

                            myRef.child("PromotionsDetails").orderByChild("id").equalTo(promotionsDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                                        child.getRef().setValue(null);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            adapter.notifyDataSetChanged();
                        }
                    });

                    alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    alertDialog.show();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(listParent.size() > 0) {
            if (newText.isEmpty())
            {
                listCopy.clear();
                listCopy.addAll(listParent);
            }
            else {
                listCopy.clear();
                for (int i = 0; i < listParent.size(); i++) {
                    if (listParent.get(i).getTitle().toString().contains(newText)) {
                        listCopy.add(listParent.get(i));
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        return true;

    }
}
