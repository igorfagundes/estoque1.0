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
        model.addAttribute("produto", new Produto()); // Adiciona um novo objeto Produto ao modelo
        return "cadastrar-produto"; // Página HTML para o formulário de cadastro
    }

    @PostMapping("/cadastrar")
    public String criarProduto(@ModelAttribute Produto produto,
            @RequestParam("imagemFile") MultipartFile imagemFile,
            @RequestParam("codigoQrFile") MultipartFile codigoQrFile,
            Model model) {

        try {
            // Salvar imagem
            if (!imagemFile.isEmpty()) {
                String imagemPath = saveUploadedFile(imagemFile);
                produto.setImagem(imagemPath);
            }

            // Salvar código QR
            if (!codigoQrFile.isEmpty()) {
                String codigoQrPath = saveUploadedFile(codigoQrFile);
                produto.setCodigoQr(codigoQrPath);
            }

            produtoService.salvar(produto);
            model.addAttribute("mensagem", "Produto cadastrado com sucesso!");
        } catch (IOException e) {
            model.addAttribute("mensagem", "Falha ao salvar arquivo: " + e.getMessage());
        }

        return "resultado-produto"; // Página HTML para o resultado do cadastro
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        // Cria o diretório se não existir
        File folder = new File(UPLOADED_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Salva o arquivo no diretório
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Files.write(path, file.getBytes());

        return path.toString();
    }

    @GetMapping("/apagar")
    public String mostrarFormularioApagar() {
        return "apagar-produto"; // Página HTML para apagar produtos
    }

    @PostMapping("/apagar")
    public String apagarProduto(@RequestParam Long id, Model model) {
        boolean sucesso = produtoService.excluir(id);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto apagado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto"; // Página HTML para o resultado da exclusão
    }

    @GetMapping("/modificar")
    public String mostrarFormularioModificar() {
        return "modificar-produto"; // Página HTML para modificar produtos
    }

    @PostMapping("/modificar")
    public String modificarProduto(@RequestParam Long id, @ModelAttribute Produto produto, Model model) {
        boolean sucesso = produtoService.modificar(id, produto);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto modificado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto"; // Página HTML para o resultado da modificação
    }
}
