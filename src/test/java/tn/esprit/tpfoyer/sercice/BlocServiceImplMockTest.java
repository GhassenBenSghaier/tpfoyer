package tn.esprit.tpfoyer.sercice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.sercice.entity.Bloc;
import tn.esprit.tpfoyer.sercice.repository.BlocRepository;
import tn.esprit.tpfoyer.sercice.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BlocServiceImplMockTest {
    @Mock
    BlocRepository blocRepository;
    @InjectMocks
    BlocServiceImpl blocService;
    Bloc bloc = new Bloc( );
    List<Bloc> listUsers = new ArrayList<Bloc>() {
        {


    add(new Bloc(1L, "aa", 2L));
    add(new Bloc());}};

    @Test
    public void testRetrieveBloc() {
        Mockito.when(blocRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bloc));

        Bloc bloc1 = blocService.retrieveBloc(1L);
        Assertions.assertNotNull(bloc1);
    }


}
