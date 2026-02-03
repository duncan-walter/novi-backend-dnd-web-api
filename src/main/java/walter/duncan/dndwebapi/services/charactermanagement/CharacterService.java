package walter.duncan.dndwebapi.services.charactermanagement;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterPortraitModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterPortraitEntity;
import walter.duncan.dndwebapi.exceptions.ResourceNotFoundException;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.services.BaseService;
import walter.duncan.dndwebapi.services.filemanagement.FileSystemStorageService;
import walter.duncan.dndwebapi.services.charactermanagement.factories.CharacterFactory;
import walter.duncan.dndwebapi.services.filemanagement.StorageService;

@Service
public class CharacterService extends BaseService<CharacterEntity, Long, CharacterRepository> {
    private final StorageService storageService;
    private final CharacterPersistenceMapper mapper;
    private final CharacterFactory characterFactory;

    protected CharacterService(
            CharacterRepository repository,
            FileSystemStorageService storageService,
            CharacterPersistenceMapper mapper,
            CharacterFactory characterFactory
    ) {
        super(repository, CharacterEntity.class);
        this.storageService = storageService;
        this.mapper = mapper;
        this.characterFactory = characterFactory;
    }

    public List<CharacterModel> findAll() {
        return this.repository.findAll().stream().map(this.characterFactory::create).toList();
    }

    public CharacterModel findById(Long id) {
        return this.characterFactory.create(this.findByIdOrThrow(id));
    }

    @Transactional
    public CharacterModel create(CharacterRequestDto requestDto) {
        var model = this.characterFactory.create(requestDto);
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.characterFactory.create(persistedEntity);
    }

    @Transactional
    public CharacterModel update(Long id, CharacterRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.characterFactory.create(id, requestDto);

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.characterFactory.create(this.repository.save(persistedEntity));
    }

    @Transactional
    public void deleteById(Long id) {
        this.existsByIdOrThrow(id);
        this.repository.deleteById(id);
    }

    // Since we store the portrait on the filesystem we have to think about how this is linked in the database
    // The intended flow is store new file > update character portrait reference > delete old file
    // This ensures that there is never an invalid reference to a file that doesn't exist.
    @Transactional
    public CharacterModel updatePortrait(Long id, MultipartFile portrait) {
        CharacterPortraitModel.validateUpload(portrait);
        var persistedEntity = this.findByIdOrThrow(id);

        // Store file
        var newFileName = this.storageService.storeFile(portrait);

        var portraitEntity = persistedEntity.getPortrait();
        var oldStoredFileName = portraitEntity != null ? portraitEntity.getStoredFileName() : null;

        if (portraitEntity == null) {
            portraitEntity = new CharacterPortraitEntity();
            portraitEntity.setCharacter(persistedEntity);
            persistedEntity.setPortrait(portraitEntity);
        }

        portraitEntity.setOriginalFileName(portrait.getOriginalFilename());
        portraitEntity.setStoredFileName(newFileName);
        portraitEntity.setMimeType(portrait.getContentType());

        // Update character portrait reference
        this.repository.save(persistedEntity);

        // Remove old file if applicable
        if (oldStoredFileName != null) {
            this.storageService.removeFile(oldStoredFileName);
        }

        return this.characterFactory.create(persistedEntity);
    }

    @Transactional
    public Resource getPortrait(Long id) {
        var persistedEntity = this.findByIdOrThrow(id);

        if (persistedEntity.getPortrait() == null) {
            throw new ResourceNotFoundException(String.format("Character portrait for character with id %s not found.", id));
        }

        return this.storageService.loadFile(persistedEntity.getPortrait().getStoredFileName());
    }
}