package aston.lab.clientserver.grpc;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.repository.ClientRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ClientServiceGrpcImpl extends ClientServiceGrpc.ClientServiceImplBase {

    private final ClientRepository clientRepository;

    @Override
    public void createClient(CreateClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        Client client = Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        client = clientRepository.save(client);

        ClientResponse response = toClientResponse(client);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getClient(GetClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        Client client = clientRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ClientResponse response = toClientResponse(client);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateClient(UpdateClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        Client client = clientRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client = clientRepository.save(client);

        ClientResponse response = toClientResponse(client);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteClient(DeleteClientRequest request, StreamObserver<DeleteClientResponse> responseObserver) {
        clientRepository.deleteById(request.getId());
        DeleteClientResponse response = DeleteClientResponse.newBuilder().setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ClientResponse toClientResponse(Client client) {
        return ClientResponse.newBuilder()
                .setId(client.getId())
                .setName(client.getName())
                .setEmail(client.getEmail())
                .build();
    }

}
