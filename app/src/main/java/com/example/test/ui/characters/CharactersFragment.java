package com.example.test.ui.characters;

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
import com.example.test.adapters.RecyclerCharactersListAdapter;
import com.example.test.api.APIClient;
import com.example.test.api.charactersApi;
import com.example.test.databinding.FragmentCharactersBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.characters.ResponseCharacters;
import com.example.test.utils.Dialogs;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersFragment extends Fragment implements RecyclerViewBaseClickListener {

    public charactersApi charactersApi;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private MaterialDialog dialog = null;

    private FragmentCharactersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // INITIALIZED

        CharactersViewModel charactersViewModel = new ViewModelProvider(this).get(CharactersViewModel.class);

        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        charactersApi = APIClient.getClient().create(charactersApi.class);

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // CREATED

        // Petición para obtener personajes
        this.getCharactersList();
    }

    /**
     * Método para solicitar al servidor el listado de personajes
     */
    public void getCharactersList() {

        dialog = Dialogs.showDialogProgress(getResources().getString(R.string.loading), getContext()).build();
        if (dialog != null) dialog.show();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            Call<ResponseCharacters> call = charactersApi.getCharacters();

            call.enqueue(new Callback<ResponseCharacters>() {
                @Override
                public void onResponse(@NonNull Call<ResponseCharacters> call, @NonNull Response<ResponseCharacters> response) {

                    handler.post(() -> {

                        switch (response.code()) {

                            case HttpURLConnection.HTTP_OK:

                                // Utilizar un linearLayout
                                layoutManager = new LinearLayoutManager(getContext());
                                binding.charactersList.setHasFixedSize(false);
                                binding.charactersList.setLayoutManager(layoutManager);

                                // Especificar un adaptador
                                adapter = new RecyclerCharactersListAdapter(CharactersFragment.this, getContext(), response.body().getResultsCharacters());
                                binding.emptyCharactersList.emptyText.setText(getResources().getString(R.string.message_empty_list));
                                binding.charactersList.setEmptyView(binding.emptyCharactersList.listEmpty);
                                binding.charactersList.setAdapter(adapter);

                                binding.charactersListRefreshLayout.setRefreshing(false);
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
                public void onFailure(@NonNull Call<ResponseCharacters> call, @NonNull Throwable t) {
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
    }
}