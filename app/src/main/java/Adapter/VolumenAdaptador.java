package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import Models.Usuario;
import Models.Volumen;
import uteq.solutions.recyclercardview.R;

public class VolumenAdaptador extends RecyclerView.Adapter<VolumenAdaptador.VolumenViewHolder> {
    private Context Ctx;
    private List<Volumen> lstVolumenes;

    public VolumenAdaptador(Context mCtx, List<Volumen> volumenes) {
        this.lstVolumenes = volumenes;
        Ctx = mCtx;
    }

    @Override
    public VolumenAdaptador.VolumenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lyt_volumen, null);
        return new VolumenAdaptador.VolumenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VolumenAdaptador.VolumenViewHolder holder, int position) {
        Volumen volumen = lstVolumenes.get(position);
        holder.txtTituloVol.setText(volumen.getTitulo());
        holder.txtFechaPub.setText(volumen.getFecha());
        holder.txtDoi.setText(volumen.getDoi());
        Glide.with(Ctx)
                .load(volumen.getUrlPortada())
                .into(holder.imgPortadaVol);
    }

    @Override
    public int getItemCount() {
        return lstVolumenes.size();
    }

    class VolumenViewHolder extends RecyclerView.ViewHolder {
        TextView txtTituloVol, txtFechaPub, txtDoi;
        ImageView imgPortadaVol;
        public VolumenViewHolder(View itemView) {
            super(itemView);
            txtTituloVol = itemView.findViewById(R.id.txtTituloVol);
            txtFechaPub = itemView.findViewById(R.id.txtFechaPub);
            txtDoi = itemView.findViewById(R.id.txtDoi);
            imgPortadaVol = itemView.findViewById(R.id.imgPortadaVol);
        }
    }
}
