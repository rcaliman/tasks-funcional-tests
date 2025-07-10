package br.ce.wcaquino.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        URL gridUrl = new URL("http://localhost:4444/");
        WebDriver driver = new RemoteWebDriver(gridUrl, options);
        driver.navigate().to("http://172.17.0.1:8001/tasks");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            // fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);

        } finally {
            // fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);

        } finally {
            // fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);

        } finally {
            // fechar o browser
            driver.quit();
        }
    }

    @Test
    public void deveRemoverTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // inserir tarefa
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

            // remover a Tarefa
            driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
            message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
            // fechar o browser
            driver.quit();
        }
    }
}
