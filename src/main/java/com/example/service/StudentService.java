package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Bu method bizga student malumotlarini data base ga
     * create qilish uchun ishlatilyapdi.
     *
     * @param dto StudentDTO typedagi parametr qabul qiladi @RequestBody anatatsyasi bizga
     *            json yoki xml formatdagi malumotni StudentDTO ga ozgartrib beradi.
     * @return -> ResponseEntity <StudentDTO>
     */
    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(LocalDateTime.now());
        studentRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    /**
     * Barcha studentlar haqidagi nalumotlarni database dan olib StudentController ga qaytarish ishlatilyapdi
     *
     * @return ResponseEntity
     */
    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> entityList = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setPhone(entity.getPhone());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * id si bo'yicha Studentni olish uchun ishlatilyapti
     *
     * @param id Integer
     * @return ResponseEntity
     */
    public StudentDTO getStudentById(Integer id) {
        Optional<StudentEntity> studentEntityById = studentRepository.findById(id);
        if (studentEntityById.isPresent()) {
            StudentEntity studentEntity = studentEntityById.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(studentEntity.getId());
            studentDTO.setAge(studentEntity.getAge());
            studentDTO.setName(studentEntity.getName());
            studentDTO.setSurname(studentEntity.getSurname());
            studentDTO.setPhone(studentEntity.getPhone());
            studentDTO.setCreatedDate(studentEntity.getCreatedDate());
            return studentDTO;
        } else {
            throw new AppBadException("Bundat student mavjud emas!!");
        }
    }

    /**
     *  id orqali studentni delete qilish uchun ishlatilgan method.
     * @param id Integer
     * @return ResponseEntity
     */
    public boolean deleteStudentById(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            throw new AppBadException("Bunday id li student topilmadi!");
        }
    }

    /**
     * id orqali update qiluvchi method.
     * @param id Integer
     * @param dto StudentDTO
     * @return ResponseEntity
     */
    public boolean updateStudentById(Integer id,StudentDTO dto){
        if(studentRepository.existsById(id)){
            StudentEntity newStudent=new StudentEntity();
            newStudent.setId(id);
            newStudent.setAge(dto.getAge());
            newStudent.setName(dto.getName());
            newStudent.setSurname(dto.getSurname());
            newStudent.setPhone(dto.getPhone());
            newStudent.setCreatedDate(dto.getCreatedDate());
            studentRepository.save(newStudent);
            return true;
        }else {
            throw new AppBadException("Bunday id li student topilmadi");
        }
    }


}
