package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/principal/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    private static final String UPLOADED_FOLDER = "uploads/";

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "listar-produtos";
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar-produto";
    }

    @PostMapping("/cadastrar")
    public String criarProduto(@ModelAttribute Produto produto,
            @RequestParam("imagemFile") MultipartFile imagemFile,
            @RequestParam("codigoQrFile") MultipartFile codigoQrFile,
            Model model) {

        try {
            if (!imagemFile.isEmpty()) {
                String imagemPath = saveUploadedFile(imagemFile);
                produto.setImagem(imagemPath);
            }

            if (!codigoQrFile.isEmpty()) {
                String codigoQrPath = saveUploadedFile(codigoQrFile);
                produto.setCodigoQr(codigoQrPath);
            }

            produtoService.salvar(produto);
            model.addAttribute("mensagem", "Produto cadastrado com sucesso!");
        } catch (IOException e) {
            model.addAttribute("mensagem", "Falha ao salvar arquivo: " + e.getMessage());
        }

        return "resultado-produto";
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        File folder = new File(UPLOADED_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Files.write(path, file.getBytes());

        return path.toString();
    }

    @GetMapping("/excluir")
    public String mostrarFormularioExcluir() {
        return "excluir-produto";
    }

    @PostMapping("/excluir")
    public String excluirProduto(@RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String codigoBarras,
            Model model) {

        Produto produto = null;

        if (id != null) {
            produto = produtoService.buscarPorId(id);
        } else if (nome != null && !nome.isEmpty()) {
            List<Produto> produtos = produtoService.buscarPorNome(nome);
            if (produtos.size() == 1) {
                produto = produtos.get(0); // Se apenas um produto for encontrado
            } else if (produtos.size() > 1) {
                model.addAttribute("mensagem", "Mais de um produto encontrado com o mesmo nome. Refine sua busca.");
                return "resultado-exclusao"; // Retorna mensagem se houver mais de um resultado
            }
        } else if (codigoBarras != null && !codigoBarras.isEmpty()) {
            produto = produtoService.buscarPorCodigoBarras(codigoBarras);
        }

        if (produto != null) {
            boolean excluido = produtoService.excluir(produto.getId());
            model.addAttribute("mensagem", excluido ? "Produto excluído com sucesso!" : "Erro ao excluir o produto.");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }

        return "resultado-exclusao";
    }

    @GetMapping("/modificar/{id}")
    public String mostrarFormularioModificar(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "modificar-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
            return "resultado-produto";
        }
    }

    @PostMapping("/modificar/{id}")
    public String modificarProduto(@PathVariable Long id, @ModelAttribute Produto produto, Model model) {
        boolean sucesso = produtoService.modificar(id, produto);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto modificado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto";
    }

    @GetMapping("/resultado-exclusao")
    public String mostrarResultadoExclusao(@RequestParam("id") Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
        }
        return "resultado-exclusao";

    }
}
