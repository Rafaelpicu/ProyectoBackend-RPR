package edu.es.eoi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.entity.ArticulosPedido;
import edu.es.eoi.marketplace.entity.Pedido;
import edu.es.eoi.marketplace.entity.Usuario;
import edu.es.eoi.marketplace.repository.ArticuloRepository;
import edu.es.eoi.marketplace.repository.ArticulosPedidoRepository;
import edu.es.eoi.marketplace.repository.PedidoRepository;
import edu.es.eoi.marketplace.repository.UsuarioRepository;
import edu.es.eoi.marketplace.service.ArticuloService;
import edu.es.eoi.marketplace.service.PedidoService;
import edu.es.eoi.marketplace.service.UsuarioService;

@SpringBootTest
class MarketplaceApplicationTests {
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	ArticuloRepository articuloRepo;
	
	@Autowired
	ArticuloService articuloSer;
	
	@Autowired
	ArticulosPedidoRepository cantidadRepo;
	
	@Autowired
	PedidoService pedidoSer;
	
	@Autowired
	UsuarioService usuarioSer;
	
	
	@Test
	void testUsuarioRepo() {
					
		Usuario prueba=new Usuario();
		prueba.setNombre("test");
		prueba.setPassword("password");
	
		usuarioRepo.save(prueba);
		
		
		Usuario usuario = usuarioRepo.findById(prueba.getId()).get();			
		Assertions.assertEquals("test",usuario.getNombre());
		
		usuario.setNombre("RPR");
		usuarioRepo.save(usuario);
		
		Assertions.assertEquals("RPR",usuarioRepo.findById(usuario.getId()).get().getNombre());
				
		usuarioRepo.delete(usuario);		
		
	}
	
	@Test
	void testUsuarioContains() {
		System.out.println (usuarioRepo.findByNombreContaining(""));
	}
	
	@Test
	void testArticuloRepo () {
		Articulo prueba = new Articulo();
		prueba.setNombre("Maceta");
		prueba.setPrecio(4);
		prueba.setStock(17);
		
		articuloRepo.save(prueba);
		
		Articulo articulo = articuloRepo.findById(prueba.getId()).get();
		Assertions.assertEquals("Maceta", articulo.getNombre());
		
		articulo.setPrecio(6);
		articuloRepo.save(articulo);
		
		Assertions.assertEquals(6, articuloRepo.findById(articulo.getId()).get().getPrecio());
		articuloRepo.delete(articulo);
	}
	@Test
	void testArticuloContains() {
		System.out.println (articuloRepo.findByNombreContaining(""));
	}
	
	
	@Test
	void testPedidoEIntermediaRepo () {
		Pedido prueba = new Pedido();
		prueba.setFecha(LocalDate.now());
		prueba.setNombre("P38");
		
		Usuario usuario = usuarioRepo.findById(3).get();
		prueba.setUsuario(usuario);
		
	
		List <ArticulosPedido> articulos = new ArrayList <ArticulosPedido>();
		ArticulosPedido cantidad = new ArticulosPedido();
		cantidad.setCantidad(5);
		cantidad.setArticulo(articuloRepo.findById(5).get());
		cantidad.setPedido(prueba);
		articulos.add(cantidad);
		
		
		prueba.setArticulosPedido(articulos);
		pedidoRepo.save(prueba);
		
		Assertions.assertEquals("P38", prueba.getNombre());
		Assertions.assertEquals(5, cantidad.getCantidad());
		
		
		cantidadRepo.delete(cantidad);
		pedidoRepo.delete(prueba);
		
	
	}
	@Test
	void testPedidoContains() {
		System.out.println (pedidoRepo.findByNombreContaining(""));
	}
	
	
	@Test
	void testServiceArticulo () {
		Articulo articulo = new Articulo ();
		articulo.setNombre("test");
		articulo.setPrecio(3);
		articulo.setStock(5);
		
		articuloRepo.save(articulo);
		
		ArticuloDto dto = articuloSer.findById(articulo.getId());
		Assertions.assertEquals("test", dto.getNombre());
		articuloRepo.delete(articulo);
		
	}
	
	@Test
	void testServiceUsuario () {
		Usuario prueba=new Usuario();
		prueba.setNombre("test");
		prueba.setPassword("password");
		
		usuarioRepo.save(prueba);
		
		UsuarioDto usuario = usuarioSer.findById(prueba.getId());
		Assertions.assertEquals("test", usuario.getNombre());
		usuarioRepo.delete(prueba);
		
	}
	
	@Test
	void testServiceUsuario2 () {
		Usuario prueba=new Usuario();
		prueba.setNombre("Nombre");
		prueba.setPassword("Con");
		
		usuarioRepo.save(prueba);
		
		UsuarioDto usuario = usuarioSer.findById(prueba.getId());
		Assertions.assertEquals("Nombre", usuario.getNombre());
		usuarioRepo.delete(prueba);
		
	}
	
		
	}