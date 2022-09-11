package com.team8.mini_tiktok.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.entity.RankList;

import java.util.List;

/**
 * @introduction: RankList适配器
 * @author: T19
 * @time: 2022.09.10 14:40
 */
public class RankListAdapter extends BaseAdapter {

    //当前Activity
    private Context mContext;
    private List<RankList> mData;

    //list item 类型标志
    public static final int TYPE_FILM = 1;
    public static final int TYPE_TV = 2;
    public static final int TYPE_VARIETY = 3;


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

    @Override
    public int getItemViewType(int position) {
        if ("1".equals(mData.get(position).getType())) {
            return TYPE_FILM;
        } else if ("2".equals(mData.get(position).getType())){
            return TYPE_TV;
        } else {
            return TYPE_VARIETY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        RankList rankEntity = mData.get(i);

        ViewHolderFilm holderFilm = null;
        ViewHolderTv holderTv = null;
        ViewHolderVariety holderVariety = null;

        //TODO：使用RecycleView优化
        if (itemView == null){//首次需加载xml布局
            switch (type) {
                case TYPE_FILM:
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
                    break;
                case TYPE_TV:
                    holderTv = new ViewHolderTv();
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_tv, viewGroup, false);
                    holderTv.imgTvPoster = itemView.findViewById(R.id.img_li_tv_poster);
                    holderTv.tvTvName= itemView.findViewById(R.id.tv_li_tv_name);
                    holderTv.tvTvNameEn = itemView.findViewById(R.id.tv_li_tv_name_en);
                    holderTv.tvTvDirectors = itemView.findViewById(R.id.tv_li_tv_directors);
                    holderTv.tvTvActors = itemView.findViewById(R.id.tv_li_tv_actors);
                    holderTv.tvTvAreas = itemView.findViewById(R.id.tv_li_tv_areas);
                    holderTv.tvTvReleaseTime = itemView.findViewById(R.id.tv_li_tv_release_time);
                    holderTv.tvTvDHot = itemView.findViewById(R.id.tv_li_tv_discussion_hot);
                    holderTv.tvTvTHot = itemView.findViewById(R.id.tv_li_tv_topic_hot);
                    itemView.setTag(holderTv);
                    break;
                case TYPE_VARIETY:
                    holderVariety = new ViewHolderVariety();
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_variety, viewGroup, false);
                    holderVariety.imgVarietyPoster = itemView.findViewById(R.id.img_li_variety_poster);
                    holderVariety.tvVarietyName= itemView.findViewById(R.id.tv_li_variety_name);
                    holderVariety.tvVarietyNameEn = itemView.findViewById(R.id.tv_li_variety_name_en);
                    holderVariety.tvVarietyDirectors = itemView.findViewById(R.id.tv_li_variety_directors);
                    holderVariety.tvVarietyDHot = itemView.findViewById(R.id.tv_li_variety_discussion_hot);
                    holderVariety.tvVarietyTHot = itemView.findViewById(R.id.tv_li_variety_topic_hot);
                    itemView.setTag(holderVariety);
                    break;
            }
        } else {
            switch (type){
                case TYPE_FILM:
                    holderFilm = (ViewHolderFilm) itemView.getTag();
                    break;
                case TYPE_TV:
                    holderTv = (ViewHolderTv) itemView.getTag();
                    break;
                case TYPE_VARIETY:
                    holderVariety = (ViewHolderVariety) itemView.getTag();
                    break;
            }
        }

        switch (type){
            case TYPE_FILM:
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
                break;
            case TYPE_TV:
                Picasso.with(mContext)
                        .load(rankEntity.getPoster())
                        .into(holderTv.imgTvPoster);
                holderTv.tvTvName.setText(rankEntity.getName());
                holderTv.tvTvNameEn.setText(rankEntity.getName_en());
                holderTv.tvTvReleaseTime.setText(rankEntity.getRelease_date());
                holderTv.tvTvDHot.setText(rankEntity.getDiscussion_hot());
                holderTv.tvTvTHot.setText(rankEntity.getTopic_hot());
                holderTv.tvTvDirectors.setText(rankEntity.getDirectorsString());
                holderTv.tvTvActors.setText(rankEntity.getActorsString());
                holderTv.tvTvAreas.setText(rankEntity.getAreasString());
                break;
            case TYPE_VARIETY:
                Picasso.with(mContext)
                        .load(rankEntity.getPoster())
                        .into(holderVariety.imgVarietyPoster);
                holderVariety.tvVarietyName.setText(rankEntity.getName());
                holderVariety.tvVarietyNameEn.setText(rankEntity.getName_en());
                holderVariety.tvVarietyDHot.setText(rankEntity.getDiscussion_hot());
                holderVariety.tvVarietyTHot.setText(rankEntity.getTopic_hot());
                holderVariety.tvVarietyDirectors.setText(rankEntity.getDirectorsString());
                break;
        }

        return itemView;
    }

    public class ViewHolderFilm extends ViewHolderItem{
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
    public class ViewHolderTv extends ViewHolderItem{
        private TextView tvTvName;
        private TextView tvTvNameEn;
        private TextView tvTvDirectors;
        private TextView tvTvActors;
        private TextView tvTvAreas;
        private TextView tvTvReleaseTime;
        private TextView tvTvDHot;
        private TextView tvTvTHot;
        private ImageView imgTvPoster;

        public ViewHolderTv() {
        }

        public ViewHolderTv(View itemView){

        }
    }
    public class ViewHolderVariety extends ViewHolderItem{
        private TextView tvVarietyName;
        private TextView tvVarietyNameEn;
        private TextView tvVarietyDirectors;
        private TextView tvVarietyDHot;
        private TextView tvVarietyTHot;
        private ImageView imgVarietyPoster;

        public ViewHolderVariety() {
        }

        public ViewHolderVariety(View itemView){

        }
    }

    public abstract class ViewHolderItem{}
}
