package com.team8.mini_tiktok.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.activity.BaseActivity;
import com.team8.mini_tiktok.entity.RankList;

import java.util.List;

/**
 * @introduction: RankList适配器
 * @author: T19
 * @time: 2022.09.10 14:40
 */
public class RankListAdapter extends BaseAdapter {

    private List<RankList> mData;
    private Context mContext;

    public RankListAdapter(List<RankList> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        RankList rankEntity = mData.get(i);

        ViewHolderFilm holderFilm = null;
        if (itemView == null){//首次需加载xml布局
            holderFilm = new ViewHolderFilm();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_film, viewGroup, false);
            holderFilm.imgFilmPoster = itemView.findViewById(R.id.img_li_film_poster);
            holderFilm.tvFilmName = itemView.findViewById(R.id.tv_li_film_name);
            holderFilm.tvFilmDirectors = itemView.findViewById(R.id.tv_li_film_directors);
            holderFilm.tvFilmActors = itemView.findViewById(R.id.tv_li_film_actors);
            holderFilm.tvFilmAreas = itemView.findViewById(R.id.tv_li_film_areas);
            holderFilm.tvFilmReleaseTime = itemView.findViewById(R.id.tv_li_film_release_time);
            holderFilm.tvFilmDHot = itemView.findViewById(R.id.tv_li_film_discussion_hot);
            holderFilm.tvFilmTHot = itemView.findViewById(R.id.tv_li_film_topic_hot);
            itemView.setTag(holderFilm);
        } else {
            holderFilm = (ViewHolderFilm) itemView.getTag();
        }
        Picasso.with(mContext)
                .load(rankEntity.getPoster())
                .into(holderFilm.imgFilmPoster);
        holderFilm.tvFilmName.setText(rankEntity.getName());
        holderFilm.tvFilmReleaseTime.setText(rankEntity.getRelease_date());
        holderFilm.tvFilmDHot.setText(rankEntity.getDiscussion_hot());
        holderFilm.tvFilmTHot.setText(rankEntity.getTopic_hot());
        holderFilm.tvFilmDirectors.setText(rankEntity.getDirectorsString());
        holderFilm.tvFilmActors.setText(rankEntity.getActorsString());
        holderFilm.tvFilmAreas.setText(rankEntity.getAreasString());
        return itemView;
    }

    public class ViewHolderFilm {
        private TextView tvFilmName;
        private TextView tvFilmDirectors;
        private TextView tvFilmActors;
        private TextView tvFilmAreas;
        private TextView tvFilmReleaseTime;
        private TextView tvFilmDHot;
        private TextView tvFilmTHot;
        private ImageView imgFilmPoster;

        public ViewHolderFilm() {
        }

        public ViewHolderFilm(View itemView){

        }
    }
}
