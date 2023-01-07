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
import uteq.solutions.recyclercardview.R;

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.UsuarioViewHolder> {
    private Context Ctx;
    private List<Usuario> lstUsuarios;

    public UsuarioAdaptador(Context mCtx, List<Usuario> usuarios) {
        this.lstUsuarios = usuarios;
        Ctx = mCtx;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lyitem_usuario, null);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        Usuario usuario = lstUsuarios.get(position);
        holder.textViewName.setText(usuario.getNombre());
        holder.textViewMail.setText(usuario.getEmail());
        holder.textViewURLAvatar.setText(usuario.getWebsite());
        Glide.with(Ctx)
                .load(usuario.getUrlavatar())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstUsuarios.size();
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewURLAvatar, textViewMail;
        ImageView imageView;
        public UsuarioViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewURLAvatar = itemView.findViewById(R.id.txtAvatar);
            textViewMail = itemView.findViewById(R.id.txtMail);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }
    }
}

