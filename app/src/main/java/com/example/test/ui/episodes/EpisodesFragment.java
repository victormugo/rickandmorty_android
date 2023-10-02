package com.example.test.ui.episodes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.test.R;
import com.example.test.adapters.RecyclerEpisodesListAdapter;
import com.example.test.api.APIClient;
import com.example.test.api.episodesApi;
import com.example.test.databinding.FragmentEpisodesBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.episodes.ResponseEpisodes;
import com.example.test.models.episodes.VoEpisodes;
import com.example.test.utils.Dialogs;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesFragment extends Fragment implements RecyclerViewBaseClickListener {

    public episodesApi episodesApi;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private MaterialDialog dialog = null;

    List<VoEpisodes> list;

    private FragmentEpisodesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EpisodesViewModel episodesViewModel = new ViewModelProvider(this).get(EpisodesViewModel.class);

        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        episodesApi = APIClient.getClient().create(episodesApi.class);

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // CREATED

        // Petición para obtener episodios
        this.getEpisodesList();
    }

    /**
     * Método para solicitar al servidor el listado de episodios
     */
    public void getEpisodesList() {

        dialog = Dialogs.showDialogProgress(getResources().getString(R.string.loading), getContext()).build();
        if (dialog != null) dialog.show();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            Call<ResponseEpisodes> call = episodesApi.episodes();
            call.enqueue(new Callback<ResponseEpisodes>() {
                @Override
                public void onResponse(@NonNull Call<ResponseEpisodes> call, @NonNull Response<ResponseEpisodes> response) {

                    handler.post(() -> {

                        switch (response.code()) {

                            case HttpURLConnection.HTTP_OK:

                                // Utilizar un linearLayout
                                layoutManager = new LinearLayoutManager(getContext());
                                binding.episodesList.setHasFixedSize(false);
                                binding.episodesList.setLayoutManager(layoutManager);

                                list = response.body().getResultsEpisodes();

                                // Especificar un adaptador
                                adapter = new RecyclerEpisodesListAdapter(EpisodesFragment.this, getContext(), list);
                                binding.emptyEpisodesList.emptyText.setText(getResources().getString(R.string.message_empty_list));
                                binding.episodesList.setEmptyView(binding.emptyEpisodesList.listEmpty);
                                binding.episodesList.setAdapter(adapter);

                                binding.episodesListRefreshLayout.setRefreshing(false);
                                break;

                            case HttpURLConnection.HTTP_UNAUTHORIZED:
                                break;

                            case HttpURLConnection.HTTP_FORBIDDEN:
                                break;

                            default:
                                // Mostrar mensaje de error
                        }

                        if (dialog != null) dialog.dismiss();
                    });
                }

                @Override
                public void onFailure(@NonNull Call<ResponseEpisodes> call, @NonNull Throwable t) {
                    call.cancel();
                    if (dialog != null) dialog.dismiss();
                }
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void recyclerViewItemClicked(int position) {
        // Episodio seleccionado del listado

        VoEpisodes episode = list.get(position);

        Intent episodeId = new Intent(getActivity().getApplicationContext(), EpisodesIdActivity.class);
        episodeId.putExtra("episodeId", episode.getId());
        getActivity().startActivity(episodeId);
    }
}