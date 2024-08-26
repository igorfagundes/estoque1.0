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
import java.util.Optional;

@Controller
@RequestMapping("/principal/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Diretório para salvar arquivos
    private static final String UPLOADED_FOLDER = "uploads/";

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        // Lógica para listar todos os produtos
        return "produtos-list";
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
            produto = produtoService.buscarPorNome(nome);
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

    @GetMapping("/modificar")
    public String mostrarFormularioModificar() {
        return "modificar-produto";
    }

    @PostMapping("/modificar")
    public String modificarProduto(@RequestParam Long id, @ModelAttribute Produto produto, Model model) {
        boolean sucesso = produtoService.modificar(id, produto);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto modificado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto";
    }
}
