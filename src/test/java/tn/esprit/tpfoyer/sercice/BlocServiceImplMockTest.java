package tn.esprit.tpfoyer.sercice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

//@ExtendWith(MockitoExtension.class)
//public class BlocServiceImplMockTest {
//    @Mock
//    BlocRepository blocRepository;
//    @InjectMocks
//    BlocServiceImpl blocService;
//    Bloc bloc = new Bloc( );
//    List<Bloc> listUsers = new ArrayList<Bloc>() {
//        {
//
//
//    add(new Bloc(1L, "aa", 2L));
//    add(new Bloc());}};
//
//    @Test
//    public void testRetrieveBloc() {
//        Mockito.when(blocRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bloc));
//
//        Bloc bloc1 = blocService.retrieveBloc(1L);
//        Assertions.assertNotNull(bloc1);
//    }
//
//
//}
@ExtendWith(MockitoExtension.class)
public class BlocServiceImplMockTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    Bloc bloc;
    List<Bloc> listBlocs;

    @BeforeEach
    public void setup() {
        bloc = new Bloc(1L, "Test Bloc", 5L);
        listBlocs = new ArrayList<>();
        listBlocs.add(bloc);
        listBlocs.add(new Bloc(2L, "Another Bloc", 10L));
    }

    @Test
    public void testRetrieveAllBlocs() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);

        List<Bloc> retrievedBlocs = blocService.retrieveAllBlocs();
        Assertions.assertEquals(2, retrievedBlocs.size());
        Assertions.assertEquals("Test Bloc", retrievedBlocs.get(0).getNomBloc());
    }

    @Test
    public void testRetrieveBlocsSelonCapacite() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);

        List<Bloc> retrievedBlocs = blocService.retrieveBlocsSelonCapacite(6);
        Assertions.assertEquals(1, retrievedBlocs.size());
        Assertions.assertEquals("Another Bloc", retrievedBlocs.get(0).getNomBloc());
    }

    @Test
    public void testAddBloc() {
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc);

        Bloc addedBloc = blocService.addBloc(bloc);
        Assertions.assertNotNull(addedBloc);
        Assertions.assertEquals("Test Bloc", addedBloc.getNomBloc());
    }

    @Test
    public void testModifyBloc() {
        Bloc modifiedBloc = new Bloc(1L, "Modified Bloc", 5L);
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(modifiedBloc);

        Bloc updatedBloc = blocService.modifyBloc(modifiedBloc);
        Assertions.assertNotNull(updatedBloc);
        Assertions.assertEquals("Modified Bloc", updatedBloc.getNomBloc());
    }

    @Test
    public void testRemoveBloc() {
        Long blocId = 1L;
        Mockito.doNothing().when(blocRepository).deleteById(blocId);

        blocService.removeBloc(blocId);
        Mockito.verify(blocRepository, Mockito.times(1)).deleteById(blocId);
    }

    @Test
    public void testTrouverBlocsSansFoyer() {
        List<Bloc> blocsSansFoyer = List.of(new Bloc(3L, "Bloc Sans Foyer", 8L));
        Mockito.when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocsSansFoyer);

        List<Bloc> result = blocService.trouverBlocsSansFoyer();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Bloc Sans Foyer", result.get(0).getNomBloc());
    }

    @Test
    public void testTrouverBlocsParNomEtCap() {
        List<Bloc> blocsParNomEtCap = List.of(new Bloc(4L, "Specific Bloc", 10L));
        Mockito.when(blocRepository.findAllByNomBlocAndCapaciteBloc("Specific Bloc", 10L)).thenReturn(blocsParNomEtCap);

        List<Bloc> result = blocService.trouverBlocsParNomEtCap("Specific Bloc", 10L);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Specific Bloc", result.get(0).getNomBloc());
    }
}
