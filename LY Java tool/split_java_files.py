import os
import re


def split_java_files(merged_file_path):
    """分离合并的Java文件到以txt文件名为目录的路径"""
    # 从txt文件路径中提取文件名（不含扩展名）作为输出目录
    output_dir = os.path.splitext(os.path.basename(merged_file_path))[0]

    with open(merged_file_path, "r", encoding="utf-8") as f:
        lines = f.readlines()

    blocks = []
    current_block = None

    # 分割文件内容为独立代码块
    for line in lines:
        stripped = line.strip()
        if stripped.startswith("// ") and stripped.endswith(".java"):
            if current_block:
                blocks.append(current_block)
            current_block = {"filename": stripped[3:], "content": []}
        elif stripped == "// FILE_END":
            if current_block:
                blocks.append(current_block)
                current_block = None
        else:
            if current_block:
                current_block["content"].append(line.rstrip("\n"))

    if current_block:
        blocks.append(current_block)

    # 创建输出目录
    os.makedirs(output_dir, exist_ok=True)

    # 写入分离后的文件
    for block in blocks:
        content = "\n".join(block["content"]).strip()
        filename = block["filename"]

        # 提取包路径
        package_match = re.search(r"^package\s+([\w\.]+)\s*;", content, re.MULTILINE)
        if package_match:
            package_path = package_match.group(1).replace(".", "/")
            full_path = output_dir
        else:
            full_path = output_dir

        os.makedirs(full_path, exist_ok=True)
        file_path = os.path.join(full_path, filename)

        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)

    print(f"成功分离 {len(blocks)} 个文件到目录：{output_dir}")


if __name__ == "__main__":
    merged_file = input("请输入合并后的txt文件路径：").strip()

    if os.path.isfile(merged_file):
        split_java_files(merged_file)
    else:
        print("错误：指定的合并文件不存在")
