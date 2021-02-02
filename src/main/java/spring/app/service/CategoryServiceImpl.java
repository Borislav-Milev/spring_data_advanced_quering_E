package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.app.constants.FilePaths;
import spring.app.constants.Messages;
import spring.app.domain.entity.Category;
import spring.app.repository.CategoryRepository;
import spring.app.service.contract.CategoryService;
import spring.app.util.contract.FileUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public int getCount(){
        return Math.toIntExact(this.categoryRepository.count());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T findEntityById(Long id) {
        return (T) this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void seedEntities() {
        if(this.categoryRepository.count() != 0){
            System.out.println(Messages.FILLED_CATEGORIES);
            return;
        }
        this.fileUtil.readFileContent(FilePaths.CATEGORIES_FILE_PATH)
                .forEach(element ->{
                    Category category = new Category(element);
                    this.categoryRepository.saveAndFlush(category);
                });
    }
}
